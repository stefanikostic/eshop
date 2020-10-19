package emt.proekt.eshop.shopmanagement.domain.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserEmailAlreadyExistsException extends RuntimeException {

    public UserEmailAlreadyExistsException() {
        super("Email provided for the User account already exists.");
    }
}
