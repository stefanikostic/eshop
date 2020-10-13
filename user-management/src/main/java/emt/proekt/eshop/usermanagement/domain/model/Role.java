package emt.proekt.eshop.usermanagement.domain.model;

import emt.proekt.eshop.sharedkernel.domain.base.AbstractEntity;
import emt.proekt.eshop.sharedkernel.domain.base.DomainObjectId;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "role")
@NoArgsConstructor
@Getter
public class Role extends AbstractEntity<RoleId> {

    @Column(nullable = false)
    private String roleName;

    public Role(String roleName) {
        super(DomainObjectId.randomId(RoleId.class));
        this.roleName = roleName;
    }

    @Override
    public RoleId id() {
        return id;
    }
}
