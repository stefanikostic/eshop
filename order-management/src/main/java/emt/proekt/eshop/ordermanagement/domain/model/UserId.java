package emt.proekt.eshop.ordermanagement.domain.model;

import emt.proekt.eshop.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class UserId extends DomainObjectId {
    private UserId() {
        super(DomainObjectId.randomId(UserId.class).toString());
    }
    public UserId(String id) {
        super(id);
    }
}
