package emt.proekt.eshop.sharedkernel.infra.eventlog;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import emt.proekt.eshop.sharedkernel.domain.base.DomainEvent;
import emt.proekt.eshop.sharedkernel.infra.jackson.RawJsonDeserializer;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.IOException;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name="event_log")
public class StoredDomainEvent {

    private static final int DOMAIN_EVENT_JSON_MAX_LENGTH = 1024;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    @JsonProperty("id")
    private Long id;
    @Column(name = "occurred_on", nullable = false)
    @JsonProperty("occurredOn")
    private Instant occurredOn;
    @Column(name = "domain_event_class_name", nullable = false)
    @JsonProperty("domainEventClass")
    private String domainEventClassName;
    @Column(name = "domain_event_body", nullable = false, length = DOMAIN_EVENT_JSON_MAX_LENGTH)
    @JsonProperty("domainEventBody")
    @JsonRawValue
    @JsonDeserialize(using = RawJsonDeserializer.class)
    private String domainEventBody;
    @Transient
    private Class<? extends DomainEvent> domainEventClass;

    @SuppressWarnings("unused") // Used by JPA only
    private StoredDomainEvent() {
    }

    StoredDomainEvent(@NonNull DomainEvent domainEvent, @NonNull ObjectMapper objectMapper) {
        Objects.requireNonNull(domainEvent, "domainEvent must not be null");
        Objects.requireNonNull(objectMapper, "objectMapper must not be null");
        occurredOn = domainEvent.occurredOn();
        domainEventClass = domainEvent.getClass();
        domainEventClassName = domainEventClass.getName();
        try {
            domainEventBody = objectMapper.writeValueAsString(domainEvent);
        } catch (JsonProcessingException ex) {
            throw new IllegalArgumentException("Could not serialize domain event to JSON", ex);
        }
        if (domainEventBody.length() > DOMAIN_EVENT_JSON_MAX_LENGTH) {
            throw new IllegalArgumentException("Domain event JSON string is too long");
        }
    }

    @NonNull
    public Long id() {
        if (id == null) {
            throw new IllegalStateException("The StoredDomainEvent has not been saved yet");
        }
        return id;
    }

    @NonNull
    public DomainEvent toDomainEvent(@NonNull ObjectMapper objectMapper) {
        return toDomainEvent(objectMapper, domainEventClass());
    }


    @NonNull
    public <T extends DomainEvent> T toDomainEvent(@NonNull ObjectMapper objectMapper,
                                                   @NonNull Class<T> domainEventClass) {
        Objects.requireNonNull(objectMapper, "objectMapper must not be null");
        Objects.requireNonNull(domainEventClass, "domainEventClass must not be null");
        try {
            return objectMapper.readValue(domainEventBody, domainEventClass);
        } catch (IOException ex) {
            throw new IllegalStateException("Could not deserialize domain event from JSON", ex);
        }
    }

    @NonNull
    public String toJsonString() {
        return domainEventBody;
    }


    @NonNull
    public JsonNode toJsonNode(@NonNull ObjectMapper objectMapper) {
        Objects.requireNonNull(objectMapper, "objectMapper must not be null");
        try {
            return objectMapper.readTree(domainEventBody);
        } catch (IOException ex) {
            throw new IllegalStateException("Could not deserialize domain event from JSON", ex);
        }
    }


    @NonNull
    public Class<? extends DomainEvent> domainEventClass() {
        if (domainEventClass == null) {
            domainEventClass = lookupDomainEventClass();
        }
        return domainEventClass;
    }


    @NonNull
    public String domainEventClassName() {
        return domainEventClassName;
    }

    @SuppressWarnings("unchecked")
    private Class<? extends DomainEvent> lookupDomainEventClass() {
        try {
            return (Class<? extends DomainEvent>) Class.forName(domainEventClassName);
        } catch (ClassNotFoundException ex) {
            throw new IllegalStateException("Could not load domain event class", ex);
        }
    }


    @NonNull
    public Instant occurredOn() {
        return occurredOn;
    }

    @Override
    public String toString() {
        return String.format("%s[id=%d, domainEventClass=%s]", getClass().getSimpleName(), id, domainEventClassName);
    }
}
