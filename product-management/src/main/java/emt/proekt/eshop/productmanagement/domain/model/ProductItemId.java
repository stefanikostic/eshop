package emt.proekt.eshop.productmanagement.domain.model;

import emt.proekt.eshop.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class ProductItemId extends DomainObjectId {
    public ProductItemId(){
        super(DomainObjectId.randomId(ProductItemId.class).toString());
    }
    public ProductItemId(String id) {
        super(id);
    }
}
