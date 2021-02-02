package emt.proekt.eshop.sharedkernel.domain.geo;

import emt.proekt.eshop.sharedkernel.domain.base.ValueObject;
import lombok.Getter;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Objects;

@Embeddable
@Getter
public class Address implements ValueObject {

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    @Embedded
    private City city;

    @Column(name = "postalcode")
    private String postalCode;

    @SuppressWarnings("unused") // Used by JPA only.
    protected Address() {
    }

    public Address(@NonNull String address, @NonNull City city,
                    String postalCode) {
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(this.address, address.address) &&
                Objects.equals(this.city, address.city)  && this.postalCode.equals(address.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, city);
    }

    @Override
    public String toString() {
        return address +
                ", " +
                city +
                ", " +
                postalCode;
    }
}
