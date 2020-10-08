package emt.proekt.eshop.ordermanagement.domain.model;

import emt.proekt.eshop.productmanagement.domain.model.ProductItemId;
import emt.proekt.eshop.sharedkernel.domain.base.AbstractEntity;
import emt.proekt.eshop.sharedkernel.domain.financial.Price;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name="order_item")
@Getter
public class OrderItem extends AbstractEntity<OrderItemId> {

    @Embedded
    @AttributeOverride(name="id",column = @Column(name="order_id", nullable = false))
    private OrderId orderId;

    @Embedded
    @AttributeOverride(name="id",column = @Column(name="product_item_id", nullable = false))
    private ProductItemId productItemId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "price", column = @Column(name = "price")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency"))
    })
    private Price price;

    @Column(name="image_path")
    private String imagePath;

    @Column(nullable = false)
    private int quantity;

    public OrderItem() {
    }

    public OrderItem(OrderId orderId, ProductItemId productItemId, Price price, String imagePath, int quantity) {
        this.orderId = orderId;
        this.productItemId = productItemId;
        this.price = price;
        this.imagePath = imagePath;
        this.quantity = quantity;
    }

    public OrderItem(OrderItemId id, OrderId orderId, ProductItemId productItemId, Price price, String imagePath, int quantity) {
        super(id);
        this.orderId = orderId;
        this.productItemId = productItemId;
        this.price = price;
        this.imagePath = imagePath;
        this.quantity = quantity;
    }

    @Override
    public OrderItemId id() {
        return null;
    }
}
