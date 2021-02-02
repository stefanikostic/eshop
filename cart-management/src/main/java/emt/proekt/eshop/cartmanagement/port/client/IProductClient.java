package emt.proekt.eshop.cartmanagement.port.client;

import emt.proekt.eshop.cartmanagement.application.dtos.AddCartItemRequestDTO;

public interface IProductClient {
    void decrementProductQuantity(AddCartItemRequestDTO cartItem, String token);
}
