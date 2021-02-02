package emt.proekt.eshop.ordermanagement.domain.model;

import com.sun.istack.NotNull;
import emt.proekt.eshop.ordermanagement.domain.model.dtos.AddressDTO;
import emt.proekt.eshop.sharedkernel.domain.base.AbstractEntity;
import emt.proekt.eshop.sharedkernel.domain.base.DomainObjectId;
import emt.proekt.eshop.sharedkernel.domain.financial.Price;
import emt.proekt.eshop.sharedkernel.domain.geo.Address;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
public class Order extends AbstractEntity<OrderId> {

    @CreatedDate
    @NotNull
    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "user_id", nullable = false))
    private UserId userId;

    @Column(name = "date_shipping")
    private Date dateShipping;

    @Column(name = "address_shipping")
    @Embedded
    private Address addressShipping;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "token_id")
    private String tokenId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name="orderId", referencedColumnName="id")
    private Set<OrderItem> orderItems;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "price", column = @Column(name = "price_total")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency"))
    })
    private Price totalPrice;

    public Order() {
    }

    public Order(LocalDateTime dateCreated, UserId userId, Price totalPrice, Address addressShipping, String tokenId) {
        super(DomainObjectId.randomId(OrderId.class));
        this.dateCreated = dateCreated;
        this.userId = userId;
        this.status = OrderStatus.INITIATED;
        this.totalPrice = totalPrice;
        this.addressShipping = addressShipping;
        this.tokenId = tokenId;
        orderItems = new HashSet<>();
    }

    public void addOrderItem(OrderItem orderItem){
        this.orderItems.add(orderItem);
    }
//Address addressShipping, String tokenId,
    public Price getTotalPrice() {
        return totalPrice;
    }
}