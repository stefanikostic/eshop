package emt.proekt.eshop.usermanagement.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import emt.proekt.eshop.sharedkernel.domain.base.DomainEvent;
import emt.proekt.eshop.sharedkernel.infra.eventlog.RemoteEventTranslator;
import emt.proekt.eshop.sharedkernel.infra.eventlog.StoredDomainEvent;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShopCreatedEventTranslator implements RemoteEventTranslator {
    private final ObjectMapper objectMapper;

    public ShopCreatedEventTranslator(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean supports(StoredDomainEvent remoteEvent) {
        return remoteEvent.domainEventClassName().equals("emt.proekt.eshop.shopmanagement.domain.event.ShopCreated");
    }

    @Override
    public Optional<DomainEvent> translate(StoredDomainEvent remoteEvent) {
        return Optional.of(remoteEvent.toDomainEvent(objectMapper, ShopCreatedEvent.class));
    }
}
