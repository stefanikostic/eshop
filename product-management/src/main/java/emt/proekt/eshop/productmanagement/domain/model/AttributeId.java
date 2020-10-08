package emt.proekt.eshop.productmanagement.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import emt.proekt.eshop.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class AttributeId extends DomainObjectId {
    private AttributeId() {
        super(DomainObjectId.randomId(AttributeId.class).toString());
    }

    @JsonCreator
    public AttributeId(String id) {
        super(id);
    }
}
