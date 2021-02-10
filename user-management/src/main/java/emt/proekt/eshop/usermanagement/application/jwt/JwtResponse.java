package emt.proekt.eshop.usermanagement.application.jwt;

import emt.proekt.eshop.usermanagement.domain.model.ShopId;
import emt.proekt.eshop.usermanagement.domain.model.UserId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private UserId id;
    private String username;
    private String shopId;
    private String firstName;
    private String lastName;
    private String email;
    private List<GrantedAuthority> roles;

    public JwtResponse (String token, UserId id, String username, String firstName, String lastName, String email, ShopId shopId, List<GrantedAuthority> roles) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.shopId = shopId!=null ? shopId.getId() : null;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
    }
}