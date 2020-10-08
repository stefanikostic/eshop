package emt.proekt.eshop.ordermanagement.port.rest;

import emt.proekt.eshop.ordermanagement.application.OrderService;
import emt.proekt.eshop.ordermanagement.domain.model.Order;
import emt.proekt.eshop.ordermanagement.domain.model.OrderId;
import emt.proekt.eshop.ordermanagement.domain.model.UserId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable("id") String orderId) {
        return orderService.findById(new OrderId(orderId))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/findByUser/{id}")
    public ResponseEntity<Order> findOrderByUser(@PathVariable("id") String userId) {
        return orderService.findOrderByUser(new UserId(userId))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
