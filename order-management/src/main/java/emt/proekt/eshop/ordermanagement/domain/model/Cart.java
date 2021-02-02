package emt.proekt.eshop.ordermanagement.domain.model;

import emt.proekt.eshop.sharedkernel.domain.financial.Price;
import lombok.Getter;

import java.util.Set;

@Getter
public class Cart {
    private Set<CartItem> cartItems;
    private Price total;

}
