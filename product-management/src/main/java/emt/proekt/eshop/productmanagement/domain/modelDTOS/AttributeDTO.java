package emt.proekt.eshop.productmanagement.domain.modelDTOS;

import emt.proekt.eshop.sharedkernel.domain.base.DomainObjectId;
import lombok.Data;

@Data
public class AttributeDTO<ID extends DomainObjectId> {

    private String id;
    private String attributeName;
    private String attributeValue;

    public AttributeDTO(ID id,
                       String attributeName,
                        String attributeValue) {
        this.id = (id == null) ? null : id.getId();
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
    }
}
