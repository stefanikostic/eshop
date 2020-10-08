package emt.proekt.eshop.cartmanagement.domain.repository;

import emt.proekt.eshop.cartmanagement.domain.model.Cart;
import emt.proekt.eshop.cartmanagement.domain.model.CartId;
import emt.proekt.eshop.cartmanagement.domain.model.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, CartId> {
    Optional<Cart> findTopByUserId(UserId userId);
}
