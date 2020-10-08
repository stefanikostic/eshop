package emt.proekt.eshop.productmanagement.domain.model;

import emt.proekt.eshop.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class ProductImageId extends DomainObjectId {
    public ProductImageId(){
        super(DomainObjectId.randomId(ProductImageId.class).toString());
    }
    public ProductImageId(String id) {
        super(id);
    }
}
