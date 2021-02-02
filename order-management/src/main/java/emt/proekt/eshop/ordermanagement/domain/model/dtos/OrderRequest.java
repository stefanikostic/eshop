package emt.proekt.eshop.ordermanagement.domain.model.dtos;

import emt.proekt.eshop.sharedkernel.domain.geo.Address;
import lombok.Getter;

@Getter
public class OrderRequest {

    private String userId;
    private String stripeToken;
    private AddressDTO addressShipping;
}
