package emt.proekt.eshop.cartmanagement.domain.model;

import com.sun.istack.NotNull;
import emt.proekt.eshop.sharedkernel.domain.base.AbstractEntity;
import emt.proekt.eshop.sharedkernel.domain.financial.Price;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cart_item")
@Getter
public class CartItem extends AbstractEntity<CartItemId> {

    @Embedded
    @AttributeOverride(name="id",column = @Column(name="cart_id", nullable = false))
    private CartId cartId;

    @Embedded
    @AttributeOverride(name="id",column = @Column(name="product_item_id", nullable = false))
    private ProductItemId productItemId;

    @CreatedDate
    @NotNull
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated = new Date();

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "price", column = @Column(name = "price")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency"))
    })
    private Price price;

    @Column(name="image_path")
    private String imagePath;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    public CartItem() {}

    public CartItem(CartId cartId, ProductItemId productItemId, Date dateCreated, Price price, String imagePath, int quantity) {
        this.cartId = cartId;
        this.productItemId = productItemId;
        this.dateCreated = dateCreated;
        this.price = price;
        this.imagePath = imagePath;
        this.quantity = quantity;
    }

    public CartItem(CartItemId id, CartId cartId, ProductItemId productItemId, Date dateCreated, Price price, String imagePath, int quantity) {
        super(id);
        this.cartId = cartId;
        this.productItemId = productItemId;
        this.dateCreated = dateCreated;
        this.price = price;
        this.imagePath = imagePath;
        this.quantity = quantity;
    }

    @Override
    public CartItemId id() {
        return null;
    }
}
