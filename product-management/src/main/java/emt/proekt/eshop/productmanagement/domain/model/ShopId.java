package emt.proekt.eshop.productmanagement.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import emt.proekt.eshop.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class ShopId extends DomainObjectId {
    public ShopId() {
        super(DomainObjectId.randomId(ShopId.class).toString());
    }
    @JsonCreator
    public ShopId(String id) {
        super(id);
    }
}
