package emt.proekt.eshop.shopmanagement.domain.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductTableNotSavedException extends RuntimeException {

    public ProductTableNotSavedException()
    {
        super("Bad attributes provided for the Product.");
    }
}
