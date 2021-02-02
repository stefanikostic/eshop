package emt.proekt.eshop.cartmanagement.port.client;

import emt.proekt.eshop.cartmanagement.application.dtos.AddCartItemRequestDTO;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ProductClient implements IProductClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductClient.class);

    private final RestTemplate restTemplate;
    private final String serverUrl;

    public ProductClient(@Value("${app.product-management.url}") String serverUrl,
                         @Value("${app.product-management.connect-timeout-ms}") int connectTimeout,
                         @Value("${app.product-management.read-timeout-ms}") int readTimeout) {
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
    public void decrementProductQuantity(AddCartItemRequestDTO cartItem, String token) {

        HttpHeaders headers = new HttpHeaders();
        String[] tokenArr = token.split(" ");
        headers.setBearerAuth(tokenArr[tokenArr.length - 1]);

        HttpEntity<AddCartItemRequestDTO> entity = new HttpEntity<>(cartItem, headers);

        restTemplate.exchange(uri().path("/products/decrementProductQuantity").build().toUri(), HttpMethod.PUT, entity,
                new ParameterizedTypeReference<AddCartItemRequestDTO>() {
                }).getBody();
    }
}
