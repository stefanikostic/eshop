package emt.proekt.eshop.ordermanagement.application;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OrderNotSavedException extends RuntimeException {

    public OrderNotSavedException(String message)
    {
        super(message);
    }
}
