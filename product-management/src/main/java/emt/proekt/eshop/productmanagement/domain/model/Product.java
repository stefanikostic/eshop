package emt.proekt.eshop.productmanagement.domain.model;

import emt.proekt.eshop.sharedkernel.domain.base.AbstractEntity;
import emt.proekt.eshop.sharedkernel.domain.financial.Price;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name="product")
public class Product extends AbstractEntity<ProductId> {
    @Column(name="product_name", nullable = false)
    private String name;

    @Column(name = "deleted", nullable = false)
    private boolean deleted;

    @Column(name="product_description")
    private String productDescription;

    @Embedded
    @AttributeOverride(name="id",column = @Column(name="shop_id", nullable = false))
    private ShopId shopId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "price", column = @Column(name = "price")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency"))
    })
    private Price price;

    public Product() {

    }

    public Product(ProductId productId, String name, boolean deleted, String productDescription, ShopId shopId, Price price){
        super(productId);
        this.name = name;
        this.deleted = deleted;
        this.productDescription = productDescription;
        this.shopId = shopId;
        this.price = price;
    }

    public Product(String name, boolean deleted, String productDescription, ShopId shopId, Price price) {
        this.name = name;
        this.deleted = deleted;
        this.productDescription = productDescription;
        this.shopId = shopId;
        this.price = price;
    }

    public Product(ProductId productId){
        super(productId);
        this.deleted = false;
    }

    public void setDeleted(boolean deleted){
        this.deleted = deleted;
    }

    @Override
    public ProductId id() {
        return null;
    }
}
