package emt.proekt.eshop.sharedkernel.infra.eventlog;


import emt.proekt.eshop.sharedkernel.domain.base.RemoteEventLog;
import org.springframework.stereotype.Service;

@Service
public interface RemoteEventLogService {

    String source();

    RemoteEventLog currentLog(long lastProcessedId);

}
