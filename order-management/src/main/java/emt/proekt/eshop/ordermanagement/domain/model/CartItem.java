package emt.proekt.eshop.ordermanagement.domain.model;

import emt.proekt.eshop.sharedkernel.domain.financial.Price;
import lombok.Getter;

@Getter
public class CartItem {
    private ProductItemId productItemId;
    private int quantity;
    private Price price;
}
