package emt.proekt.eshop.sharedkernel.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserTableNotSavedException extends RuntimeException{

    public UserTableNotSavedException() {
        super("Bad attributes provided for the User.");
    }
}
