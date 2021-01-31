package emt.proekt.eshop.ordermanagement.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import emt.proekt.eshop.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class ProductItemId extends DomainObjectId {

    public ProductItemId() {
        super(DomainObjectId.randomId(ProductItemId.class).toString());
    }
    @JsonCreator
    public ProductItemId(String id) {
        super(id);
    }
}
