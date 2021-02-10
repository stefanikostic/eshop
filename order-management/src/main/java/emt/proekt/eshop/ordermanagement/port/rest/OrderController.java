package emt.proekt.eshop.ordermanagement.port.rest;

import emt.proekt.eshop.ordermanagement.application.OrderService;
import emt.proekt.eshop.ordermanagement.domain.model.Cart;
import emt.proekt.eshop.ordermanagement.domain.model.Order;
import emt.proekt.eshop.ordermanagement.domain.model.OrderId;
import emt.proekt.eshop.ordermanagement.domain.model.UserId;
import emt.proekt.eshop.ordermanagement.domain.model.dtos.OrderRequest;
import emt.proekt.eshop.ordermanagement.port.client.ICartClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    private final OrderService orderService;
    private final ICartClient cartClient;

    public OrderController(OrderService orderService, ICartClient cartClient) {
        this.orderService = orderService;
        this.cartClient = cartClient;
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

    @PostMapping(path = "/create")
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest orderRequest, @RequestHeader("Authorization") String token){
        try {
            Cart cart = cartClient.findByUserId(new UserId(orderRequest.getUserId()), token);
            if(cart.getCartItems().size() == 0){
                return new ResponseEntity<>("Your cart is empty", HttpStatus.BAD_REQUEST);
            }
            String id = orderService.createOrder(orderRequest, cart);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
