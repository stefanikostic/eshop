package emt.proekt.eshop.usermanagement.domain.model;

import com.sun.istack.NotNull;
import emt.proekt.eshop.sharedkernel.domain.base.AbstractEntity;
import emt.proekt.eshop.sharedkernel.domain.geo.Address;

import lombok.Getter;
import lombok.NonNull;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
public class User extends AbstractEntity<UserId> {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @CreatedDate
    @NotNull
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated = new Date();

    @Embedded
    @NonNull
    private Address fullAddress;

    @Embedded
    @AttributeOverride(name="id",column = @Column(name="shop_id", nullable = true))
    private ShopId shopId;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Role> roles = new ArrayList<>();

    public User() {}

    public User(String firstName, String lastName, String email, String password, Date dateCreated, Address fullAddress, ShopId shopId, List<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dateCreated = dateCreated;
        this.fullAddress = fullAddress;
        this.shopId = shopId;
        this.roles = roles;
    }

    public User(UserId userId, String firstName, String lastName, String email, String password, Date dateCreated, Address fullAddress, ShopId shopId, List<Role> roles) {
        super(userId);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dateCreated = dateCreated;
        this.fullAddress = fullAddress;
        this.shopId = shopId;
        this.roles = roles;
    }

    @Override
    public UserId id() {
        return null;
    }
}
