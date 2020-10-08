package emt.proekt.eshop.productmanagement.domain.model;

import emt.proekt.eshop.sharedkernel.domain.base.AbstractEntity;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Table(name="attribute")
public class Attribute extends AbstractEntity<AttributeId> {

    @Column(name="attribute_name")
    private String name;

    @Column(name="attribute_value")
    private String value;

    public Attribute() {}

    public Attribute(AttributeId attributeId) {
        super(attributeId);
    }

    public Attribute(String name, String value){
        this.name = name;
        this.value = value;
    }
    @Override
    public AttributeId id() {
        return null;
    }
}
