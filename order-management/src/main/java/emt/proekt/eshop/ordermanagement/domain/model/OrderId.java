package emt.proekt.eshop.ordermanagement.domain.model;

import emt.proekt.eshop.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class OrderId extends DomainObjectId {
    private OrderId() {
        super("");
    }

    public OrderId(String id) {
        super(id);
    }
}
