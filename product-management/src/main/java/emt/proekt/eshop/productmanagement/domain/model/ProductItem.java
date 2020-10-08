package emt.proekt.eshop.productmanagement.domain.model;

import emt.proekt.eshop.sharedkernel.domain.base.AbstractEntity;
import emt.proekt.eshop.sharedkernel.domain.financial.Price;
import lombok.Getter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Table(name = "product_item")
public class ProductItem extends AbstractEntity<ProductItemId> {

    @Column(name="item_name")
    private String name;

    @Column(name = "deleted", nullable = false)
    private boolean deleted;

    @Column(name = "price", nullable = false)
    private Price price;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Embedded
    @AttributeOverride(name="id",column = @Column(name="product_id", nullable = false))
    private ProductId productId;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Attribute> attributes = new HashSet<>();

    public ProductItem(ProductItemId id, String name, boolean deleted, Price price, int quantity, ProductId productId, Set<Attribute> attributes) {
        super(id);
        this.name = name;
        this.deleted = deleted;
        this.price = price;
        if(quantity < 0){
            throw new IllegalArgumentException("Quantity cannot be negative!");
        }
        this.quantity = quantity;
        this.productId = productId;
        this.attributes = attributes;
    }

    public ProductItem(String name, boolean deleted, Price price, int quantity, ProductId productId, Set<Attribute> attributes) {
        this.name = name;
        this.deleted = deleted;
        this.price = price;
        if(quantity < 0){
            throw new IllegalArgumentException("Quantity cannot be negative!");
        }
        this.quantity = quantity;
        this.productId = productId;
        this.attributes = attributes;
    }

    public ProductItem() {}

    public void subtractQuantity(int quantity){
        if(quantity > this.quantity){
            throw new RuntimeException("unsupported quantity");
        }
        this.quantity -= quantity;
    }

    public void addQuantity(int quantity){
        this.quantity += quantity;
    }

    @Override
    public ProductItemId id() {
        return id;
    }

    public Price subTotal(){
        return price.multiply(quantity);
    }
}
