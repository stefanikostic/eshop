package emt.proekt.eshop.sharedkernel.domain.base;

import lombok.NonNull;

import java.time.Instant;

public interface DomainEvent {

    @NonNull
    Instant occurredOn();
}
