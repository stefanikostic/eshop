package emt.proekt.eshop.productmanagement.domain.model;

import emt.proekt.eshop.sharedkernel.domain.base.AbstractEntity;
import emt.proekt.eshop.sharedkernel.domain.base.DomainObjectId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@NoArgsConstructor
@Table(name="attributes")
public class Attribute extends AbstractEntity<AttributeId> {

    @Column(name="attribute_name")
    private String attributeName;

    @Column(name="attribute_value")
    private String attributeValue;

    public Attribute(String name, String value){
        super(DomainObjectId.randomId(AttributeId.class));
        this.attributeName = name;
        this.attributeValue = value;
    }
    @Override
    public AttributeId id() {
        return id;
    }
}
