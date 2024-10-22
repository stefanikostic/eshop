package emt.proekt.eshop.ordermanagement.domain.model;

import emt.proekt.eshop.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class OrderItemId extends DomainObjectId {
    private OrderItemId() {
        super(DomainObjectId.randomId(OrderItemId.class).toString());
    }

    public OrderItemId(String id) {
        super(id);
    }
}
