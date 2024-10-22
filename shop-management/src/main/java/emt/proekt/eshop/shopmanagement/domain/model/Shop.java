package emt.proekt.eshop.shopmanagement.domain.model;

import emt.proekt.eshop.sharedkernel.domain.base.AbstractEntity;
import emt.proekt.eshop.sharedkernel.domain.base.DomainObjectId;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "shop")
public class Shop extends AbstractEntity<ShopId> {

    @Column(name = "shop_name")
    private String name;

    @Column(name = "shop_bank_account", nullable = false)
    private String shopBankAccount;

    @Column(name = "shop_UTN", nullable = false)
    private String shopUTN;

    @Column(name = "shop_description")
    private String shopDescription;

    @Column(name = "shop_image")
    private String shopLogoImage;

    @Column(name = "created_date")
    private String createdDate;

    public Shop() {
    }

    public void setShopImage(String shopImage){
        this.shopLogoImage = shopImage;
    }
    public Shop(String name, String shopBankAccount, String shopUTN, String shopDescription, String shopLogoImage, LocalDateTime createdDate) {
        super(DomainObjectId.randomId(ShopId.class));
        this.name = name;
        this.shopBankAccount = shopBankAccount;
        this.shopUTN = shopUTN;
        this.shopDescription = shopDescription;
        this.shopLogoImage = shopLogoImage;
        this.createdDate = createdDate.toString();
    }
}