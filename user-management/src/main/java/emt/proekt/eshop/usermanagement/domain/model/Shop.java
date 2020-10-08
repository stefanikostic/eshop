package emt.proekt.eshop.usermanagement.domain.model;

import emt.proekt.eshop.productmanagement.domain.model.Category;
import lombok.Getter;

@Getter
public class Shop {
    private ShopId shopId;

    private String name;

    private String shopBankAccount;

    private String shopUTN;

    private String shopDescription;

    private String shopImage;

    private Category shopCategory;
}
