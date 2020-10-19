package emt.proekt.eshop.usermanagement.integration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import emt.proekt.eshop.sharedkernel.domain.base.DomainEvent;
import emt.proekt.eshop.usermanagement.domain.model.ShopId;
import emt.proekt.eshop.usermanagement.domain.model.UserId;
import lombok.Getter;
import lombok.NonNull;

import java.time.Instant;
import java.util.Objects;

@Getter
public class ShopCreatedEvent implements DomainEvent {
    @JsonProperty("shopId")
    private final ShopId shopId;
    @JsonProperty("userId")
    private final UserId userId;
    @JsonProperty("occurredOn")
    private final Instant occurredOn;

    @JsonCreator
    public ShopCreatedEvent(@JsonProperty("shopId") @NonNull ShopId shopId,
                            @JsonProperty("userId") @NonNull UserId userId,
                            @JsonProperty("occurredOn") @NonNull Instant occurredOn) {
        this.shopId = Objects.requireNonNull(shopId, "shopId must not be null");
        this.userId = Objects.requireNonNull(userId, "userId must not be null");;
        this.occurredOn = Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }

    @NonNull
    public ShopId shopId() {
        return shopId;
    }

    @Override
    @NonNull
    public Instant occurredOn() {
        return occurredOn;
    }

}
