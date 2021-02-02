package emt.proekt.eshop.cartmanagement.application.dtos;

import emt.proekt.eshop.sharedkernel.domain.financial.Price;
import lombok.Getter;


@Getter
public class AddCartItemRequestDTO {

    private String productItemId;
    private Price price;
    private int cartItemQuantity;
    private String productName;
    private String userId;

    public AddCartItemRequestDTO(String productItemId, int cartItemQuantity,Price price, String productName, String userId) {
        this.productItemId = productItemId;
        this.price = price;
        this.cartItemQuantity = cartItemQuantity;
        this.productName = productName;
        this.userId = userId;
    }

}
