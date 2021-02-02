package emt.proekt.eshop.ordermanagement.domain.model;

import emt.proekt.eshop.sharedkernel.domain.base.AbstractEntity;
import emt.proekt.eshop.sharedkernel.domain.base.DomainObjectId;
import emt.proekt.eshop.sharedkernel.domain.financial.Price;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name="order_item")
@Getter
public class OrderItem extends AbstractEntity<OrderItemId> {

    @Column(name = "orderId", nullable = false)
    private String orderId;

    @Embedded
    @AttributeOverride(name="id",column = @Column(name="product_item_id", nullable = false))
    private ProductItemId productItemId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "price", column = @Column(name = "price")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency"))
    })
    private Price price;

    @Column(nullable = false)
    private int quantity;

    public OrderItem() {
    }

    public OrderItem(String orderId, ProductItemId productItemId, Price price, int quantity) {
        super(DomainObjectId.randomId(OrderItemId.class));
        this.orderId = orderId;
        this.productItemId = productItemId;
        this.price = price;
        this.quantity = quantity;
    }


}
