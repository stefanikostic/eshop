package emt.proekt.eshop.usermanagement.domain.model;

import emt.proekt.eshop.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class RoleId extends DomainObjectId {
    private RoleId() {
        super(DomainObjectId.randomId(UserId.class).toString());
    }
    public RoleId(String id) {
        super(id);
    }
}
