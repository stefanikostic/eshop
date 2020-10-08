package emt.proekt.eshop.cartmanagement.domain.repository;

import emt.proekt.eshop.cartmanagement.domain.model.CartItem;
import emt.proekt.eshop.cartmanagement.domain.model.CartItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, CartItemId> {
}
