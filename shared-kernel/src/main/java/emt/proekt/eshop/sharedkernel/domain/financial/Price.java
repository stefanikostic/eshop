package emt.proekt.eshop.sharedkernel.domain.financial;

import lombok.Getter;
import lombok.NonNull;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@Embeddable
@Getter
public class Price {
    @Enumerated(value = EnumType.STRING)
    private Currency currency;

    private int price;


    public Price(@NonNull int price, Currency currency) {
        if (currency == null) {
            currency = Currency.EUROS;
        }
        this.price = price;
        this.currency = currency;
    }

    public Price() {

    }

    public Price add(Price amount) {
        if (!currency.equals(amount.currency)) {
            throw new IllegalArgumentException("Prices are from different currency");
        }
        return new Price(price + amount.price, currency);
    }

    public Price substract(Price amount) {
        if (!currency.equals(amount.currency)) {
            throw new IllegalArgumentException("Prices are from different currency");
        }
        return new Price(price - amount.price, currency);
    }

    public Price multiply(int q) {
        return new Price(price * q, currency);
    }

    public Price calculateDiscount(int discount) {
        Price newPrice = new Price(price - (price * discount) / 100, currency);
        return newPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price amount = (Price) o;
        return price == amount.price &&
                currency == amount.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, price);
    }

    @Override
    public String toString() {
        return String.format("%s %d", currency, price);
    }
}
