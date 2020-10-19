package emt.proekt.eshop.shopmanagement.domain.model;

import emt.proekt.eshop.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class ShopId extends DomainObjectId {
    public ShopId(){
        super(DomainObjectId.randomId(ShopId.class).toString());
    }
    public ShopId(String id) {
        super(id);
    }
}
