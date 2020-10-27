package emt.proekt.eshop.shopmanagement.domain.model;

import emt.proekt.eshop.sharedkernel.domain.base.AbstractEntity;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name="category")
public class Category extends AbstractEntity<CategoryId> {

    @Column(name="category_name", nullable = false)
    private String name;

    @Embedded
    @AttributeOverride(name="id", column = @Column(name="category_id", nullable = false))
    private CategoryId superCategoryId;

    public Category(String name){
        this.name = name;
    }

    public Category(String name, CategoryId superCategoryId) {
        this.name = name;
        this.superCategoryId = superCategoryId;
    }

    public Category() {}

    public Category(CategoryId categoryId){
        super(categoryId);
    }

    public void setSuperCategory(CategoryId categoryId){
        this.superCategoryId = categoryId;
    }

    @Override
    public CategoryId id() {
        return null;
    }
}
