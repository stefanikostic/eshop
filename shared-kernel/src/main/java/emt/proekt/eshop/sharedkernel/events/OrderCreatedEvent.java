package emt.proekt.eshop.sharedkernel.events;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCreatedEvent {

    private String occurredOn;
    private String userId;

    public OrderCreatedEvent(String occurredOn, String userId) {
        this.userId = userId;
        this.occurredOn = occurredOn;
    }

    public OrderCreatedEvent(){}

    @Override
    public String toString() {
        return "OrderCreatedEvent{" +
                "occurredOn='" + occurredOn + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}