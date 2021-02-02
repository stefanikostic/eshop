package emt.proekt.eshop.ordermanagement.port.client;

import emt.proekt.eshop.ordermanagement.domain.model.Cart;
import emt.proekt.eshop.ordermanagement.domain.model.UserId;
import org.springframework.beans.factory.annotation.Value;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class CartClient implements ICartClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(CartClient.class);

    private final RestTemplate restTemplate;
    private final String serverUrl;

    public CartClient(@Value("${app.cart-management.url}") String serverUrl,
                      @Value("${app.cart-management.connect-timeout-ms}") int connectTimeout,
                      @Value("${app.cart-management.read-timeout-ms}") int readTimeout) {
        this.serverUrl = serverUrl;
        restTemplate = new RestTemplate();
        var requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(connectTimeout);
        requestFactory.setReadTimeout(readTimeout);
        restTemplate.setRequestFactory(requestFactory);
    }

    private UriComponentsBuilder uri() {
        return UriComponentsBuilder.fromUriString(serverUrl);
    }


    @Override
    public Cart findByUserId(UserId id, String token) {
        try {
            HttpHeaders headers = new HttpHeaders();
            String [] tokenArr = token.split(" ");
            headers.setBearerAuth(tokenArr[tokenArr.length -1]);

            HttpEntity<String> entity = new HttpEntity<>("body", headers);

            return restTemplate.exchange(uri().path("/carts/" + id.getId()).build().toUri(), HttpMethod.GET, entity,
                    new ParameterizedTypeReference<Cart>() {
                    }).getBody();
        } catch (Exception ex) {
            LOGGER.error("Error retrieving cart by id", ex);
            return null;
        }

    }
}
