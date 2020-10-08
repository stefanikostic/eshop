package emt.proekt.eshop.ordermanagement.domain.model;

import emt.proekt.eshop.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class OrderItemId extends DomainObjectId {
    private OrderItemId() {
        super("");
    }

    public OrderItemId(String id) {
        super(id);
    }
}
