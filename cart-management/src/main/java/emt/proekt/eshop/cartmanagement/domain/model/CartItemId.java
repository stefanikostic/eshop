package emt.proekt.eshop.cartmanagement.domain.model;

import emt.proekt.eshop.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class CartItemId extends DomainObjectId {
    private CartItemId() {
        super(DomainObjectId.randomId(CartItemId.class).toString());
    }

    public CartItemId(String id) {
        super(id);
    }
}
