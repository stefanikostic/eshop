package emt.proekt.eshop.usermanagement.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import emt.proekt.eshop.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class ShopId extends DomainObjectId {
    private ShopId() {
        super(DomainObjectId.randomId(ShopId.class).toString());
    }

    public ShopId(String id) {
        super(id);
    }
}
