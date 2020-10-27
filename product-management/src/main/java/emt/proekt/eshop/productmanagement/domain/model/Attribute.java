package emt.proekt.eshop.productmanagement.domain.model;

import emt.proekt.eshop.sharedkernel.domain.base.AbstractEntity;
import emt.proekt.eshop.sharedkernel.domain.base.DomainObjectId;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    private String name;

    @Column(name="attribute_value")
    private String value;

    public Attribute(String name, String value){
        super(DomainObjectId.randomId(AttributeId.class));
        this.name = name;
        this.value = value;
    }
    @Override
    public AttributeId id() {
        return id;
    }
}
