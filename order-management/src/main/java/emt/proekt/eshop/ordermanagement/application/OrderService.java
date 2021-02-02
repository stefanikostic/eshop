package emt.proekt.eshop.ordermanagement.application;

import com.stripe.exception.StripeException;
import emt.proekt.eshop.ordermanagement.domain.model.*;
import emt.proekt.eshop.ordermanagement.domain.model.dtos.OrderRequest;
import emt.proekt.eshop.ordermanagement.domain.repository.OrderRepository;
import emt.proekt.eshop.sharedkernel.domain.geo.Address;
import emt.proekt.eshop.sharedkernel.domain.geo.City;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final PaymentService paymentService;

    public OrderService(OrderRepository orderRepository, PaymentService paymentService) {
        this.orderRepository = orderRepository;
        this.paymentService = paymentService;
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

    public void deleteOrder(@NonNull OrderId orderId) {
        Objects.requireNonNull(orderId, "orderId must not be null");
        orderRepository.deleteById(orderId);
    }

    @NonNull
    public Order saveOrder(@NonNull Order order) {
        Objects.requireNonNull(order, "order must not be null");
        return orderRepository.save(order);
    }

    @NonNull
    public Optional<Order> findOrderByUser(@NonNull UserId userId) {
        Objects.requireNonNull(userId, "userId must not be null");
        return orderRepository.findTopByUserId(userId);
    }

    @Transactional
    public void createOrder(OrderRequest orderRequest, Cart cart) throws StripeException {
        String address = orderRequest.getAddressShipping().getAddress();
        City city = new City(orderRequest.getAddressShipping().getCity());
        String postalcode = orderRequest.getAddressShipping().getPostalCode();
        Order order = new Order(LocalDateTime.now(), new UserId(orderRequest.getUserId()), cart.getTotal(), new Address(address, city, postalcode), orderRequest.getStripeToken());
        cart.getCartItems().forEach(ci -> order.addOrderItem(new OrderItem(order.id().getId(), ci.getProductItemId(), ci.getPrice(), ci.getQuantity())));
        Order created = orderRepository.save(order);
        paymentService.charge(created);
    }
}
