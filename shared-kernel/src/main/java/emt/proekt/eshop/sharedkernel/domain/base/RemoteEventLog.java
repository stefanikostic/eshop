package emt.proekt.eshop.sharedkernel.domain.base;



import emt.proekt.eshop.sharedkernel.infra.eventlog.StoredDomainEvent;

import java.util.List;

public interface RemoteEventLog {

    List<StoredDomainEvent> events();
}
