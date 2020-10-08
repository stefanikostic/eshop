package emt.proekt.eshop.ordermanagement.application;

import emt.proekt.eshop.ordermanagement.domain.model.Order;
import emt.proekt.eshop.ordermanagement.domain.model.OrderId;
import emt.proekt.eshop.ordermanagement.domain.model.UserId;
import emt.proekt.eshop.ordermanagement.domain.repository.OrderRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    @NonNull
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @NonNull
    public Optional<Order> findById(@NonNull OrderId orderId) {
        Objects.requireNonNull(orderId, "orderId must not be null");
        return orderRepository.findById(orderId);
    }

    public void deleteOrder(@NonNull OrderId orderId){
        Objects.requireNonNull(orderId, "orderId must not be null");
        orderRepository.deleteById(orderId);
    }

    @NonNull
    public Order saveOrder(@NonNull Order order){
        Objects.requireNonNull(order, "order must not be null");
        return orderRepository.save(order);
    }

    @NonNull
    public Optional<Order> findOrderByUser(@NonNull UserId userId){
        Objects.requireNonNull(userId, "userId must not be null");
        return orderRepository.findTopByUserId(userId);
    }
}
