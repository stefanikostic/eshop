package emt.proekt.eshop.ordermanagement.domain.model;

import com.sun.istack.NotNull;
import emt.proekt.eshop.sharedkernel.domain.base.AbstractEntity;
import emt.proekt.eshop.sharedkernel.domain.financial.Price;
import emt.proekt.eshop.sharedkernel.domain.geo.Address;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="order")
@Getter
public class Order extends AbstractEntity<OrderId> {

    @CreatedDate
    @NotNull
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated = new Date();

    @Embedded
    @AttributeOverride(name="id", column = @Column(name="user_id", nullable = false))
    private UserId userId;

    @Column(name = "date_shipping")
    private Date dateShipping;

    @Column(name = "address_shipping")
    private Address addressShipping;

    private OrderStatus status;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "price", column = @Column(name = "price_total")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency"))
    })
    private Price totalPrice;

    public Order() {
    }

    public Order(Date dateCreated, UserId userId, Date dateShipping, Address addressShipping, OrderStatus status, Price totalPrice) {
        this.dateCreated = dateCreated;
        this.userId = userId;
        this.dateShipping = dateShipping;
        this.addressShipping = addressShipping;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    public Order(OrderId id, Date dateCreated, UserId userId, Date dateShipping, Address addressShipping, OrderStatus status, Price totalPrice) {
        super(id);
        this.dateCreated = dateCreated;
        this.userId = userId;
        this.dateShipping = dateShipping;
        this.addressShipping = addressShipping;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    @Override
    public OrderId id() {
        return null;
    }
}
