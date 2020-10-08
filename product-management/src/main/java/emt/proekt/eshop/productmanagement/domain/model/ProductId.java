package emt.proekt.eshop.productmanagement.domain.model;

import emt.proekt.eshop.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class ProductId extends DomainObjectId {
    public ProductId(){
        super(DomainObjectId.randomId(ProductId.class).toString());
    }
    public ProductId(String id) {
        super(id);
    }
}
