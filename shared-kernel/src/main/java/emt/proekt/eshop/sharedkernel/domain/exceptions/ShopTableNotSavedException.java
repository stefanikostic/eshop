package emt.proekt.eshop.sharedkernel.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ShopTableNotSavedException extends RuntimeException{

    public ShopTableNotSavedException() {
        super("Bad attributes provided for the Shop.");
    }
}
