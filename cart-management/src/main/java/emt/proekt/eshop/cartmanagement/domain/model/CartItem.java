package emt.proekt.eshop.cartmanagement.domain.model;

import com.sun.istack.NotNull;
import emt.proekt.eshop.sharedkernel.domain.base.AbstractEntity;
import emt.proekt.eshop.sharedkernel.domain.base.DomainObjectId;
import emt.proekt.eshop.sharedkernel.domain.financial.Price;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "cart_item")
@Getter
public class CartItem extends AbstractEntity<CartItemId> {


    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "product_item_id", nullable = false))
    private ProductItemId productItemId;

    @CreatedDate
    @NotNull
    @Column(name = "date_created")
    private LocalDateTime dateCreated;


    @Column(name = "productName", nullable = false)
    private String productName;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "price", column = @Column(name = "price")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency"))
    })
    private Price price;

    @Column(name = "quantity", nullable = false)
    private int quantity;


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Price subTotal() {
        return price.multiply(quantity);
    }

    public CartItem() {
    }

    public CartItem(ProductItemId productItemId, Price price, int quantity, String productName) {
        super(DomainObjectId.randomId(CartItemId.class));

        this.productItemId = productItemId;
        this.dateCreated = LocalDateTime.now();
        this.price = price;
        this.productName = productName;
        this.quantity = quantity;
    }

}
