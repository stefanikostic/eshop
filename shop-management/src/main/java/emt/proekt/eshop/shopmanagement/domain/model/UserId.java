package emt.proekt.eshop.shopmanagement.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import emt.proekt.eshop.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class UserId extends DomainObjectId {

    private UserId() {
        super(DomainObjectId.randomId(UserId.class).toString());
    }

    @JsonCreator
    public UserId(String id) {
        super(id);
    }
}
