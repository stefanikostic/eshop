package emt.proekt.eshop.cartmanagement.domain.model;

import emt.proekt.eshop.sharedkernel.domain.base.AbstractEntity;
import emt.proekt.eshop.sharedkernel.domain.base.DomainObjectId;
import emt.proekt.eshop.sharedkernel.domain.financial.Currency;
import emt.proekt.eshop.sharedkernel.domain.financial.Price;
import lombok.Getter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    private Price total;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name="cartId", referencedColumnName="id")
    private Set<CartItem> cartItems;

    public Cart() {}

    public Cart(UserId userId) {
        super(DomainObjectId.randomId(CartId.class));
        this.userId = userId;
        this.total = new Price(0, Currency.EUROS);
        cartItems = new HashSet<>();
    }

    public void addCartItem(CartItem cartItem) {
        this.cartItems.add(cartItem);
    }

    public Price total() {
        return cartItems.stream().map(CartItem::subTotal).reduce(new Price( 0,total.getCurrency()), Price::add);
    }

    public void updateTotal() {
        this.total = total();
    }

}
