package emt.proekt.eshop.productmanagement.domain.modelDTOS;

import emt.proekt.eshop.sharedkernel.domain.base.DomainObjectId;
import lombok.Data;

@Data
public class CategoryDTO<ID extends DomainObjectId> {

    private String id;
    private String name;
    private String superCategoryId;

    public CategoryDTO(ID id,
                       String name,
                       ID superCategoryId) {
        this.id = (id == null) ? null : id.getId();
        this.name = name;
        this.superCategoryId = (superCategoryId == null) ? null : superCategoryId.getId();
    }
}
