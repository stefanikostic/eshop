package emt.proekt.eshop.productmanagement.domain.model;

import emt.proekt.eshop.sharedkernel.domain.base.AbstractEntity;
import emt.proekt.eshop.sharedkernel.domain.base.DomainObjectId;
import emt.proekt.eshop.sharedkernel.domain.financial.Price;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
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

    @Column(name = "productId")
    private String product;

    @ManyToMany
    private Set<Attribute> attributes = new HashSet<>();

    public ProductItem(boolean deleted, Price price, int quantityInStock, Set<Attribute> attributes, ProductId productId) {
        super(DomainObjectId.randomId(ProductItemId.class));
        this.deleted = deleted;
        this.price = price;
        this.product = productId.getId();
        if(quantityInStock < 0){
            throw new IllegalArgumentException("Quantity cannot be negative!");
        }
        this.quantityInStock = quantityInStock;
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
    @Column(name = "productItemId")
    public ProductItemId id() {
        return id;
    }

    public Price subTotal(){
        return price.multiply(quantityInStock);
    }
}
