package emt.proekt.eshop.ordermanagement.domain.model;

import emt.proekt.eshop.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;


@Embeddable
public class CartId extends DomainObjectId {
    private CartId() {
        super(DomainObjectId.randomId(CartId.class).toString());
    }

    public CartId(String id) {
        super(id);
    }
}


