package emt.proekt.eshop.usermanagement.domain.model;

import emt.proekt.eshop.sharedkernel.domain.base.AbstractEntity;
import lombok.Data;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "role")
@Getter
public class Role extends AbstractEntity<RoleId> {

    @Column(name="role_name", nullable = false)
    private String roleName;

    public Role() {}

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public Role(RoleId roleId, String roleName) {
        super(roleId);
        this.roleName = roleName;
    }

    @Override
    public RoleId id() {
        return null;
    }
}
