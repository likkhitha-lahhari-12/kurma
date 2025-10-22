# **Kurma Project – Current Implementation**

### 1. Overview

Kurma manages vertiports, pads, and flight assignments with a modular **Java** + Spring Boot backend and **MongoDB**.


### Tech Stack:

Java + Spring Boot

MongoDB

Lombok

REST APIs

Event Outbox pattern for future event-driven integration

### 2. Core Models

#### Vertiport

Fields: id, name, location, timezone

Supports CRUD operations

#### Pad

Fields: id, name, status (FREE/ASSIGNED), vertiportId

Updates status when assigned or freed

CRUD operations implemented

#### Flight

Fields: id, flightNumber, priority, status (NEW/ASSIGNED/DEPARTED)

CRUD operations implemented

#### PadAssignment

Fields: id, flightId, vertiportId, padId, startAt, endAt, status (AWARDED, COMPLETED, etc.)

Assign Pad → allocate pad, update statuses, create event

Complete Assignment → mark assignment completed, free pad, update flight, create event

#### EventOutbox

Fields: aggregateType, aggregateId, eventType, eventData

Stores events (kurma.pad.assigned, kurma.pad.released)

Ready for future Redis/Kafka integration

### 3. PadAssignmentServiceImpl 

**assignPad(dto):** assigns first free pad to a flight, updates Pad & Flight, stores event.

**completeAssignment(id):** completes assignment, updates Pad & Flight, stores release event.

Transactional to prevent inconsistent states.

Uses Instant for timestamps to avoid MongoDB codec issues.

### 4. Event-Driven Status

   Feature	Status
   Event creation	✅ Implemented (EventOutbox)
  
### 5. Testing

CRUD for Flights, Vertiports, Pads → tested via Postman

PadAssignment → assign, complete, update, delete → tested

EventOutbox → events verified in MongoDB after actions

### 6. Known Issues / Notes

Duplicate assignments prevented.

Events are stored but not consumed yet.

Instant timestamps used for MongoDB compatibility.
