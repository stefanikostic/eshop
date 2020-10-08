package emt.proekt.eshop.cartmanagement.domain.model;

import emt.proekt.eshop.sharedkernel.domain.financial.Price;
import lombok.Getter;

@Getter
public class ProductItem {

    private ProductItemId productItemId;

    private String name;

    private Price price;

    private int quantity;
}
