package emt.proekt.eshop.productmanagement.domain.model;


import emt.proekt.eshop.sharedkernel.domain.base.AbstractEntity;
import lombok.Getter;

import javax.persistence.*;

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
