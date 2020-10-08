package emt.proekt.eshop.cartmanagement.domain.model;

import com.sun.istack.NotNull;
import emt.proekt.eshop.sharedkernel.domain.geo.Address;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class User {
    private String firstName;

    private String lastName;

    private String email;

    private String password;

}
