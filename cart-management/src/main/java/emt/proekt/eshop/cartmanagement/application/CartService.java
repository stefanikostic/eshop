package emt.proekt.eshop.cartmanagement.application;

import emt.proekt.eshop.cartmanagement.application.dtos.AddCartItemRequestDTO;
import emt.proekt.eshop.cartmanagement.application.dtos.RemoveCartItem;
import emt.proekt.eshop.cartmanagement.domain.model.*;
import emt.proekt.eshop.cartmanagement.domain.repository.CartRepository;
import emt.proekt.eshop.cartmanagement.port.client.IProductClient;
import emt.proekt.eshop.sharedkernel.events.CartItemRemovedEvent;
import emt.proekt.eshop.sharedkernel.events.OrderCreatedEvent;
import javassist.NotFoundException;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class CartService {
    private final CartRepository cartRepository;
    private final IProductClient productClient;

    private final KafkaTemplate<String, CartItemRemovedEvent> kafkaTemplate;
    private static final String TOPIC = "removedCartItemsTopic";

    public CartService(CartRepository cartRepository, IProductClient productClient, KafkaTemplate<String, CartItemRemovedEvent> kafkaTemplate) {
        this.cartRepository = cartRepository;
        this.productClient = productClient;
        this.kafkaTemplate = kafkaTemplate;
    }


    @Transactional
    public Cart findCartByCurrentUser(String userId) {
        Optional<Cart> cart = cartRepository.findByUserId(new UserId(userId));
        if (!cart.isPresent()) {
            Cart newCart = new Cart(new UserId(userId));
            return cartRepository.save(newCart);
        }
        return cart.get();
    }

    @Transactional
    public void deleteFromCart(RemoveCartItem item) throws NotFoundException {
        try {


            Optional<Cart> cart = cartRepository.findByUserId(new UserId(item.getUserId()));
            if (!cart.isPresent()) {
                throw new NotFoundException("Cart not found");
            } else {
                for (CartItem cartItem : cart.get().getCartItems()) {
                    if (cartItem.id().getId().equals(item.getCartItemId())) {
                        cart.get().getCartItems().remove(cartItem);
                        cart.get().updateTotal();
                        cartRepository.save(cart.get());

                        kafkaTemplate.send(TOPIC, new CartItemRemovedEvent(cartItem.getProductItemId().getId(), cartItem.getQuantity(), LocalDateTime.now().toString()));
                        break;
                    }
                }

            }
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    @Transactional
    public void addToCart(AddCartItemRequestDTO cartItemRequest, String token) {
        try {
            Optional<Cart> cart = cartRepository.findByUserId(new UserId(cartItemRequest.getUserId()));
            if (!cart.isPresent()) {
                Cart newCart = new Cart(new UserId(cartItemRequest.getUserId()));
                newCart.addCartItem(
                        new CartItem
                                (new ProductItemId(
                                        cartItemRequest.getProductItemId()),
                                        cartItemRequest.getPrice(),
                                        cartItemRequest.getCartItemQuantity(),
                                        cartItemRequest.getProductName())
                );
                newCart.updateTotal();
                cartRepository.save(newCart);
            } else {
                Optional<CartItem> cartItem = cart.get().getCartItems().stream().filter(i -> i.getProductItemId().getId().equals(cartItemRequest.getProductItemId())).findFirst();

                if (cartItem.isPresent()) {
                    throw new RuntimeException();

                } else {
                    cart.get().addCartItem(
                            new CartItem
                                    (new ProductItemId(
                                            cartItemRequest.getProductItemId()),
                                            cartItemRequest.getPrice(),
                                            cartItemRequest.getCartItemQuantity(),
                                            cartItemRequest.getProductName())
                    );
                }
                cart.get().updateTotal();
                cartRepository.save(cart.get());
            }

            productClient.decrementProductQuantity(cartItemRequest, token);
        } catch (Exception exception) {
            throw new RuntimeException();
        }

    }

    @KafkaListener(topics = "orderCreatedTopic", groupId = "group_id",
            containerFactory = "orderCreatedListenerFactory")
    @org.springframework.transaction.annotation.Transactional
    public void onCartItemRemoved(OrderCreatedEvent orderCreatedEvent) {
        System.out.println("Consumed JSON Message: " + orderCreatedEvent);

        try {
            Optional<Cart> cart = cartRepository.findByUserId(new UserId(orderCreatedEvent.getUserId()));
            cart.get().clear();
            cart.get().updateTotal();
            cartRepository.save(cart.get());
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }
}
