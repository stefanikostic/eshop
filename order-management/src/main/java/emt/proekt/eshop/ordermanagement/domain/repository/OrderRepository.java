package emt.proekt.eshop.ordermanagement.domain.repository;

import emt.proekt.eshop.ordermanagement.domain.model.Order;
import emt.proekt.eshop.ordermanagement.domain.model.OrderId;
import emt.proekt.eshop.ordermanagement.domain.model.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, OrderId> {
    Optional<Order> findTopByUserId(UserId userId);
}
