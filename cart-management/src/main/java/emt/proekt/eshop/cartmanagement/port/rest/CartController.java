package emt.proekt.eshop.cartmanagement.port.rest;
import emt.proekt.eshop.cartmanagement.application.CartService;
import emt.proekt.eshop.cartmanagement.domain.model.Cart;
import emt.proekt.eshop.cartmanagement.domain.model.CartId;
import emt.proekt.eshop.cartmanagement.domain.model.UserId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> findById(@PathVariable("id") String cartId) {
        return cartService.findById(new CartId(cartId))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Cart> findAll() {
        return cartService.findAll();
    }

    @GetMapping("/findByUser/{id}")
    public ResponseEntity<Cart> findCartByUser(@PathVariable("id") String userId) {
        return cartService.findCartByUser(new UserId(userId))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
