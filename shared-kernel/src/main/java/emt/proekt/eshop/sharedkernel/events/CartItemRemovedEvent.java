package emt.proekt.eshop.sharedkernel.events;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemRemovedEvent {

    private String productItemId;
    private int quantity;
    private String occurredOn;

    public CartItemRemovedEvent(String productItemId,
                            int quantity,
                            String occurredOn) {
        this.productItemId = productItemId;
        this.quantity = quantity;
        this.occurredOn = occurredOn;
    }

    public CartItemRemovedEvent(){}

    @Override
    public String toString() {
        return "CartItemRemovedEvent{" +
                "productItemId='" + productItemId + '\'' +
                ", quantity='" + quantity + '\'' +
                ", occurredOn='" + occurredOn + '\'' +
                '}';
    }
}
