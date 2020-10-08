package emt.proekt.eshop.productmanagement.domain.model;

import emt.proekt.eshop.sharedkernel.domain.base.AbstractEntity;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Table(name="product_image")
public class ProductImage extends AbstractEntity<ProductImageId> {
    private String path;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    public ProductImage() {}

    public ProductImage(String path, Product product) {
        this.path = path;
        this.product = product;
    }

    @Override
    public ProductImageId id() {
        return null;
    }
}
