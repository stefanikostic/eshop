package emt.proekt.eshop.productmanagement.port.client;


import emt.proekt.eshop.sharedkernel.domain.base.RemoteEventLog;
import emt.proekt.eshop.sharedkernel.infra.eventlog.StoredDomainEvent;

import java.util.List;

public class RemoteEventLogImpl implements RemoteEventLog {
    private final List<StoredDomainEvent> storedDomainEvents;

    public RemoteEventLogImpl(List<StoredDomainEvent> storedDomainEvents) {
        this.storedDomainEvents = storedDomainEvents;
    }

    @Override
    public List<StoredDomainEvent> events() {
        return storedDomainEvents;
    }
}
