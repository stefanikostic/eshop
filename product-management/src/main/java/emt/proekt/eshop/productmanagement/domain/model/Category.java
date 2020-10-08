package emt.proekt.eshop.productmanagement.domain.model;

import emt.proekt.eshop.sharedkernel.domain.base.AbstractEntity;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name="category")
public class Category extends AbstractEntity<CategoryId> {

    @Column(name="category_name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name="supercategory_id")
    private Category superCategory;

    public Category(String name){
        this.name = name;
    }

    public Category(String name, Category superCategory) {
        this.name = name;
        this.superCategory = superCategory;
    }

    public Category() {}

    public Category(CategoryId categoryId){
        super(categoryId);
    }

    public void setSuperCategory(Category category){
        this.superCategory = category;
    }

    @Override
    public CategoryId id() {
        return null;
    }
}
