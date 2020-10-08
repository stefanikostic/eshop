package emt.proekt.eshop.cartmanagement.domain.model;

import emt.proekt.eshop.sharedkernel.domain.base.AbstractEntity;
import emt.proekt.eshop.sharedkernel.domain.financial.Price;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "cart")
@Getter
public class Cart extends AbstractEntity<CartId> {
    @Embedded
    @AttributeOverride(name="id",column = @Column(name="user_id", nullable = false))
    private UserId userId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "price", column = @Column(name = "sub_total")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency"))
    })
    private Price subTotal;

    public Cart() {}

    @Override
    public CartId id() {
        return null;
    }
}
