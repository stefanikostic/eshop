package emt.proekt.eshop.shopmanagement.domain.model.dto;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Data
@Getter
public class ShopCreationDTO implements Serializable {
    private String shopName;
    private String shopDescription;
    private String shopBankAccount;
    private String shopUTN;
    private String shopCategory;
}
