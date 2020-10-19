package emt.proekt.eshop.shopmanagement.domain.model;

import emt.proekt.eshop.sharedkernel.domain.base.AbstractEntity;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "shop")
public class Shop extends AbstractEntity<ShopId> {

    @Column(name = "shop_name")
    private String name;

    @Column(name="shop_bank_account", nullable = false)
    private String shopBankAccount;

    @Column(name="shop_UTN", nullable = false)
    private String shopUTN;

    @Column(name="shop_description")
    private String shopDescription;

    @Column(name="shop_image")
    private String shopLogoImage;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shop_category")
    private Category shopCategory;

    public Shop() {}

    public Shop(String name, String shopBankAccount, String shopUTN, String shopDescription, String shopLogoImage, Category shopCategory) {
        this.name = name;
        this.shopBankAccount = shopBankAccount;
        this.shopUTN = shopUTN;
        this.shopDescription = shopDescription;
        this.shopLogoImage = shopLogoImage;
        this.shopCategory = shopCategory;
    }

    public Shop(ShopId id, String name, String shopBankAccount, String shopUTN, String shopDescription, String shopLogoImage, Category shopCategory) {
        super(id);
        this.name = name;
        this.shopBankAccount = shopBankAccount;
        this.shopUTN = shopUTN;
        this.shopDescription = shopDescription;
        this.shopLogoImage = shopLogoImage;
        this.shopCategory = shopCategory;
    }

    @Override
    public ShopId id() {
        return null;
    }
}