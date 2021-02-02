package emt.proekt.eshop.cartmanagement.port.rest;
import emt.proekt.eshop.cartmanagement.application.CartService;
import emt.proekt.eshop.cartmanagement.application.dtos.AddCartItemRequestDTO;
import emt.proekt.eshop.cartmanagement.application.dtos.RemoveCartItem;
import emt.proekt.eshop.cartmanagement.domain.model.Cart;
import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<Cart> getCartFromUser(@PathVariable String userId){
        Cart cart = cartService.findCartByCurrentUser(userId);
        return ResponseEntity.ok(cart);
    }


    @PatchMapping(path="/addProductItem")
    public ResponseEntity<?> addToCart(@RequestBody AddCartItemRequestDTO cartItemRequest, @RequestHeader("Authorization") String token){
        this.cartService.addToCart(cartItemRequest, token);
        return ResponseEntity.ok().body("Cart item added");
    }

    @DeleteMapping(path="/deleteProductItem")
    public ResponseEntity<?> deleteFromCart(@RequestBody RemoveCartItem cartItemRequest) throws NotFoundException {
        this.cartService.deleteFromCart(cartItemRequest);
        return ResponseEntity.ok().body("Cart item deleted");
    }
}
