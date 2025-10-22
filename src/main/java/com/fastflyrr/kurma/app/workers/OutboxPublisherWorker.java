package com.fastflyrr.kurma.app.workers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fastflyrr.kurma.core.db.repositories.EventOutboxRepository;
import com.fastflyrr.kurma.core.models.EventOutbox;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Component
public class OutboxPublisherWorker {
    private final MongoTemplate mongoTemplate;
    private final EventOutboxRepository eventOutboxRepository;
    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper mapper = new ObjectMapper();

    @Value("${kurma.outbox.channel:kurma-events}")
    String channel;

    private final int BATCH_SIZE = 50;
    private final int MAX_RETRIES = 5;

    public OutboxPublisherWorker(MongoTemplate mongoTemplate, EventOutboxRepository eventOutboxRepository, StringRedisTemplate redisTemplate) {
        this.mongoTemplate = mongoTemplate;
        this.eventOutboxRepository = eventOutboxRepository;
        this.redisTemplate = redisTemplate;
    }

    @Scheduled(fixedDelayString = "${kurma.outbox.poll-interval-ms:2000}")
    public void publishPending(){
        // find candidate ids quickly
        List<EventOutbox> candidates = eventOutboxRepository.findByStatus("NEW");
        if(candidates.isEmpty()) return;

        int processed = 0;
        for(EventOutbox candidate: candidates){
            if(processed>=BATCH_SIZE) break;

            //automatically claim the event
            Query q = new Query(where("_id").is(candidate.getId()).and("status").is("NEW"));
            Update u = new Update().set("status","PENDING");
            FindAndModifyOptions opts = new FindAndModifyOptions().returnNew(true);

            EventOutbox claimed = mongoTemplate.findAndModify(q,u,opts,EventOutbox.class);
            if(claimed==null){
                 continue;
            }

            // publish
            try{
                String payload = mapper.writeValueAsString(Map.of(
                   "eventId", claimed.getId(),
                   "aggregateType", claimed.getAggregateType(),
                   "aggregateId", claimed.getAggregateId(),
                    "eventData", claimed.getEventData(),
                    "createdAt", claimed.getCreatedAt()
                ));
                redisTemplate.convertAndSend(channel, payload);

                claimed.setStatus("PUBLISHED");
                claimed.setPublishedAt(Instant.now());
                eventOutboxRepository.save(claimed);
            }catch(Exception e){
                //publish failed -> increase retries, maybe set to NEW so it can be retried
                claimed.setRetries(claimed.getRetries()+1);
                if(claimed.getRetries() >= MAX_RETRIES){
                    claimed.setStatus("FAILED");
                }else{
                    claimed.setStatus("NEW");
                }
                eventOutboxRepository.save(claimed);
            }
            processed++;


        }
    }
}
