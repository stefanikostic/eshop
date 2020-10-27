package emt.proekt.eshop.usermanagement.domain.model;

import emt.proekt.eshop.sharedkernel.domain.base.AbstractEntity;
import emt.proekt.eshop.sharedkernel.domain.base.DomainObjectId;
import emt.proekt.eshop.sharedkernel.domain.geo.Address;
import lombok.*;
import javax.persistence.*;
import java.util.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User extends AbstractEntity<UserId> {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Embedded
    private Address fullAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "shop_id"))
    })
    private ShopId shopId;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Role> roles;

    public User(String firstName, String lastName, String email, String password, Role role) {
        super(DomainObjectId.randomId(UserId.class));
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = new HashSet<>();
        roles.add(role);
    }

    public void setShopId(ShopId shopId) {
        this.shopId = shopId;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public void deleteRole(Role role){
        this.roles.remove(role);
    }

    @Override
    public UserId id() {
        return id;
    }
}
