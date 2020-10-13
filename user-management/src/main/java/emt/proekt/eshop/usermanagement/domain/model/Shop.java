package emt.proekt.eshop.usermanagement.domain.model;

import lombok.Getter;

@Getter
public class Shop {
    private ShopId shopId;

    private String shopName;

    private String shopBankAccount;

    private String shopUTN;

    private String shopDescription;

    private String shopLogoImage;

    //private Category shopCategory;
}
