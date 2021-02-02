package emt.proekt.eshop.productmanagement.domain.modelDTOS;

import emt.proekt.eshop.sharedkernel.domain.financial.Price;
import lombok.Getter;

@Getter
public class CartItemRequestDTO {

    private String productItemId;
    private Price price;
    private int cartItemQuantity;
    private String productName;
    private String userId;

    public CartItemRequestDTO(String productItemId, int cartItemQuantity,Price price, String productName, String userId) {
        this.productItemId = productItemId;
        this.price = price;
        this.cartItemQuantity = cartItemQuantity;
        this.productName = productName;
        this.userId = userId;
    }

}
