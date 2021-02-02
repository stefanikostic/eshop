package emt.proekt.eshop.ordermanagement.port.client;

import emt.proekt.eshop.ordermanagement.domain.model.Cart;
import emt.proekt.eshop.ordermanagement.domain.model.UserId;

public interface ICartClient {

    Cart findByUserId(UserId id, String token);
}