package emt.proekt.eshop.ordermanagement.domain.model;

import emt.proekt.eshop.sharedkernel.domain.base.DomainObjectId;
import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class OrderId extends DomainObjectId {
    public OrderId() {
        super("");
    }

    public OrderId(String id) {
        super(id);
    }
}
