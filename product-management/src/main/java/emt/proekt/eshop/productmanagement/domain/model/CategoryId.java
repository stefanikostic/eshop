package emt.proekt.eshop.productmanagement.domain.model;

import emt.proekt.eshop.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class CategoryId extends DomainObjectId {
    public CategoryId(){
        super(DomainObjectId.randomId(CategoryId.class).toString());
    }
    public CategoryId(String id) {
        super(id);
    }
}
