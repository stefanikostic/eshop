package emt.proekt.eshop.sharedkernel.domain.geo;

import com.fasterxml.jackson.annotation.JsonProperty;
import emt.proekt.eshop.sharedkernel.domain.base.ValueObject;
import lombok.Getter;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Objects;

@Embeddable
@Getter
@MappedSuperclass
public class Address implements ValueObject {

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    @Embedded
    private City city;

    @Column(name = "country")
    @Enumerated(EnumType.STRING)
    private Country country;

    @Column(name = "postal_code")
    private String postalCode;

    @SuppressWarnings("unused") // Used by JPA only.
    protected Address() {
    }

    public Address(@NonNull String address, @NonNull City city,
                   @NonNull Country country, String postalCode) {
        this.address = address;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(address, address.address) &&
                Objects.equals(city, address.city) &&
                country == address.country && postalCode.equals(address.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, city, country);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(address);
        sb.append(", ");
        sb.append(city);
        sb.append(", ");
        sb.append(country);
        sb.append(", ");
        sb.append(postalCode);
        return sb.toString();
    }

    @org.springframework.lang.NonNull
    @JsonProperty("address")
    public String address() {
        return address;
    }

    @org.springframework.lang.NonNull
    @JsonProperty("city")
    public City city() {
        return city;
    }

    @org.springframework.lang.NonNull
    @JsonProperty("country")
    public Country country() {
        return country;
    }

    @JsonProperty("postal_code")
    public String postalCode() { return postalCode; }

}
