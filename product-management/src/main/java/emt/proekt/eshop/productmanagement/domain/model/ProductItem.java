package emt.proekt.eshop.productmanagement.domain.model;

import emt.proekt.eshop.sharedkernel.domain.base.AbstractEntity;
import emt.proekt.eshop.sharedkernel.domain.base.DomainObjectId;
import emt.proekt.eshop.sharedkernel.domain.financial.Price;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "product_items")
public class ProductItem extends AbstractEntity<ProductItemId> {

    @Column(name = "deleted", nullable = false)
    private boolean deleted;

    @Column(name = "price", nullable = false)
    private Price price;

    @Column(name = "quantity", nullable = false)
    private int quantityInStock;

    @Embedded
    @AttributeOverride(name="id",column = @Column(name="product_id", nullable = false))
    private ProductId productId;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Attribute> attributes = new HashSet<>();

    public ProductItem(boolean deleted, Price price, int quantityInStock, ProductId productId, Set<Attribute> attributes) {
        super(DomainObjectId.randomId(ProductItemId.class));
        this.deleted = deleted;
        this.price = price;
        if(quantityInStock < 0){
            throw new IllegalArgumentException("Quantity cannot be negative!");
        }
        this.quantityInStock = quantityInStock;
        this.productId = productId;
        this.attributes = attributes;
    }

    public void subtractQuantity(int quantity){
        if(quantity > this.quantityInStock){
            throw new RuntimeException("unsupported quantity");
        }
        this.quantityInStock -= quantity;
    }

    public void addQuantity(int quantity){
        this.quantityInStock += quantity;
    }

    @Override
    public ProductItemId id() {
        return id;
    }

    public Price subTotal(){
        return price.multiply(quantityInStock);
    }
}
