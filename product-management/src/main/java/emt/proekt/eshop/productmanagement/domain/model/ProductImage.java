package emt.proekt.eshop.productmanagement.domain.model;

import emt.proekt.eshop.sharedkernel.domain.base.AbstractEntity;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name="product_image")
public class ProductImage extends AbstractEntity<ProductImageId> {

    private String path;

    @Embedded
    @AttributeOverride(name="id",column = @Column(name="product_id", nullable = false))
    private ProductId productId;

    public ProductImage() {}

    public ProductImage(String path, ProductId productId) {
        this.path = path;
        this.productId = productId;
    }

    public ProductImage(ProductImageId id, String path, ProductId productId) {
        super(id);
        this.path = path;
        this.productId = productId;
    }

    @Override
    public ProductImageId id() {
        return null;
    }
}
