package emt.proekt.eshop.productmanagement.domain.model;

import emt.proekt.eshop.sharedkernel.domain.base.AbstractEntity;
import emt.proekt.eshop.sharedkernel.domain.base.DomainObjectId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="product_images")
public class ProductImage extends AbstractEntity<ProductImageId> {

    private String imagePath;


    @Column(name = "productId")
    private String product;


    public ProductImage(String imagePath, ProductId productId) {
        super(DomainObjectId.randomId(ProductImageId.class));
        this.imagePath = imagePath;
        this.product = productId.getId();
    }

    @Override
    public ProductImageId id() {
        return id;
    }
}
