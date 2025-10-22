package com.fastflyrr.kurma.app.workers;



import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RedisEventListener implements MessageListener {

    private final RedisMessageListenerContainer listenerContainer;
    private final ObjectMapper mapper = new ObjectMapper();

    @Value("${kurma.outbox.channel:kurma-events}")
    private String channel;

    public RedisEventListener(RedisMessageListenerContainer listenerContainer) {
        this.listenerContainer = listenerContainer;
    }

    @PostConstruct
    public void subscribe() {
        listenerContainer.addMessageListener(this, new ChannelTopic(channel));
        System.out.println("[Kurma Consumer] Subscribed to Redis channel: " + channel);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String data = message.toString();
            Map<String, Object> event = mapper.readValue(data, new TypeReference<>() {});
            String eventType = (String) event.get("eventType");
            String aggregateId = (String) event.get("aggregateId");
            System.out.printf("[Kurma Consumer] Received %s for aggregate %s%n", eventType, aggregateId);
        } catch (Exception e) {
            System.err.println("Failed to parse Redis message: " + e.getMessage());
        }
    }
}