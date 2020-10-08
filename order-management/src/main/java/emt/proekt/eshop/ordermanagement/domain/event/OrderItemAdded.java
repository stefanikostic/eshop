package emt.proekt.eshop.ordermanagement.domain.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import emt.proekt.eshop.ordermanagement.domain.model.OrderId;
import emt.proekt.eshop.ordermanagement.domain.model.OrderItemId;
import emt.proekt.eshop.productmanagement.domain.model.ProductId;
import emt.proekt.eshop.sharedkernel.domain.base.DomainEvent;
import lombok.Getter;
import lombok.NonNull;

import java.time.Instant;

@Getter
public class OrderItemAdded implements DomainEvent {

    @JsonProperty("orderId")
    private final OrderId orderId;

    @JsonProperty
    private final OrderItemId orderItemId;

    @JsonProperty
    private final ProductId productId;

    @JsonProperty("quantity")
    private final int quantity;

    @JsonProperty("occurredOn")
    private final Instant occurredOn;

    public OrderItemAdded(OrderId orderId, OrderItemId orderItemId, ProductId productId, int quantity, Instant occurredOn) {
        this.orderId = orderId;
        this.orderItemId = orderItemId;
        this.productId = productId;
        this.quantity = quantity;
        this.occurredOn = occurredOn;
    }

    @Override
    public @NonNull Instant occurredOn() {
        return occurredOn;
    }
}
