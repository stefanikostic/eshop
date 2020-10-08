package emt.proekt.eshop.cartmanagement.application;

import emt.proekt.eshop.cartmanagement.domain.model.Cart;
import emt.proekt.eshop.cartmanagement.domain.model.CartId;
import emt.proekt.eshop.cartmanagement.domain.model.UserId;
import emt.proekt.eshop.cartmanagement.domain.repository.CartRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class CartService {
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }
    @NonNull
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    @NonNull
    public Optional<Cart> findById(@NonNull CartId cartId) {
        Objects.requireNonNull(cartId, "cartId must not be null");
        return cartRepository.findById(cartId);
    }

    public void deleteCart(@NonNull CartId cartId){
        Objects.requireNonNull(cartId, "cartId must not be null");
        cartRepository.deleteById(cartId);
    }

    @NonNull
    public Cart saveCart(@NonNull Cart cart){
        Objects.requireNonNull(cart, "cart must not be null");
        return cartRepository.save(cart);
    }

    @NonNull
    public Optional<Cart> findCartByUser(@NonNull UserId userId){
        Objects.requireNonNull(userId, "userId must not be null");
        return cartRepository.findTopByUserId(userId);
    }
}
