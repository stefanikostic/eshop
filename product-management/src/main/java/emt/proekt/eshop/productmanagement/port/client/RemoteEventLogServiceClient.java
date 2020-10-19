package emt.proekt.eshop.productmanagement.port.client;

import emt.proekt.eshop.sharedkernel.domain.base.RemoteEventLog;
import emt.proekt.eshop.sharedkernel.infra.eventlog.RemoteEventLogService;
import emt.proekt.eshop.sharedkernel.infra.eventlog.StoredDomainEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@Service
public class RemoteEventLogServiceClient implements RemoteEventLogService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RemoteEventLogServiceClient.class);

    private final String source;
    private final RestTemplate restTemplate;

    public RemoteEventLogServiceClient(@Value("${app.cart-management.url}") String source,
                                       @Value("${app.cart-management.connect-timeout-ms}") int connectTimeout,
                                       @Value("${app.cart-management.read-timeout-ms}") int readTimeout) {
        this.source = source;
        this.restTemplate = new RestTemplate();
        var requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(connectTimeout);
        requestFactory.setReadTimeout(readTimeout);
        restTemplate.setRequestFactory(requestFactory);
    }

    @Override
    public String source() {
        return source;
    }

    @Override
    public RemoteEventLog currentLog(long lastProcessedId) {
        List<StoredDomainEvent> events;
        try{
            events = restTemplate.exchange(UriComponentsBuilder.fromUriString(source).path("/api/event-log/" + lastProcessedId).build().toUri(), HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<StoredDomainEvent>>() {
                    }).getBody();
        } catch (Exception ex){
            LOGGER.error("Error retrieving events", ex);
            return new RemoteEventLogImpl(Collections.emptyList());
        }
        return new RemoteEventLogImpl(events);
    }
}
