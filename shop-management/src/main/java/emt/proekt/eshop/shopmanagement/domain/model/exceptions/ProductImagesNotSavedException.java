package emt.proekt.eshop.shopmanagement.domain.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductImagesNotSavedException extends RuntimeException{

    public ProductImagesNotSavedException()
    {
        super("Can not save the provided files");
    }
}
