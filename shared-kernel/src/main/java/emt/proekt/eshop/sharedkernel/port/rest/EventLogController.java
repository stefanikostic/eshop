package emt.proekt.eshop.sharedkernel.port.rest;

import emt.proekt.eshop.sharedkernel.infra.eventlog.DomainEventLogService;
import emt.proekt.eshop.sharedkernel.infra.eventlog.StoredDomainEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/event-log")
public class EventLogController {

    private final DomainEventLogService domainEventLogService;

    public EventLogController(DomainEventLogService domainEventLogService) {
        this.domainEventLogService = domainEventLogService;
    }

    @GetMapping(path = "/{lastProcessedId}")
    public ResponseEntity<List<StoredDomainEvent>> domainEvents(@PathVariable("lastProcessedId") long lastProcessedId){
        var responseBuilder = ResponseEntity.ok();
        return responseBuilder.body(domainEventLogService.retrieveLog(lastProcessedId));
    }

}
