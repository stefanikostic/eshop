package emt.proekt.eshop.sharedkernel.events;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ShopCreatedEvent {

    private String shopId;
    private String userId;
    private String occurredOn;

    public ShopCreatedEvent(String shopId,
                            String userId,
                            String occurredOn) {
        this.shopId = shopId;
        this.userId = userId;
        this.occurredOn = occurredOn;
    }

    public ShopCreatedEvent(){}

    @Override
    public String toString() {
        return "ShopCreatedEvent{" +
                "shopId='" + shopId + '\'' +
                ", userId='" + userId + '\'' +
                ", occurredOn=" + occurredOn +
                '}';
    }
}

