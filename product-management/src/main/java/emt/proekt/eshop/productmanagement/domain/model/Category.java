package emt.proekt.eshop.productmanagement.domain.model;

import emt.proekt.eshop.sharedkernel.domain.base.AbstractEntity;
import emt.proekt.eshop.sharedkernel.domain.base.DomainObjectId;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name="categories")
public class Category extends AbstractEntity<CategoryId> {

    @Column(name="category_name", nullable = false)
    private String name;

    @Embedded
    @AttributeOverride(name="id", column = @Column(name="super_category_id"))
    private CategoryId superCategoryId;

    public Category(String name, CategoryId superCategoryId) {
        super(DomainObjectId.randomId(CategoryId.class));
        this.name = name;
        this.superCategoryId = superCategoryId;
    }
}
