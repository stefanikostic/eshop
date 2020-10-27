package emt.proekt.eshop.productmanagement.domain.model;

import emt.proekt.eshop.sharedkernel.domain.base.AbstractEntity;
import emt.proekt.eshop.sharedkernel.domain.base.DomainObjectId;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name="product_images")
public class ProductImage extends AbstractEntity<ProductImageId> {

    private String imagePath;

    @Embedded
    @AttributeOverride(name="id",column = @Column(name="product_id", nullable = false))
    private ProductId productId;


    public ProductImage(String imagePath, ProductId productId) {
        super(DomainObjectId.randomId(ProductImageId.class));
        this.imagePath = imagePath;
        this.productId = productId;
    }

    @Override
    public ProductImageId id() {
        return id;
    }
}
