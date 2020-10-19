package emt.proekt.eshop.shopmanagement.domain.model;

import emt.proekt.eshop.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class CategoryId extends DomainObjectId {
    private CategoryId(){
        super(DomainObjectId.randomId(CategoryId.class).toString());
    }
    public CategoryId(String id) {
        super(id);
    }
}
