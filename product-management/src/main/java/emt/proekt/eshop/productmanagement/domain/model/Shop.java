package emt.proekt.eshop.productmanagement.domain.model;

import emt.proekt.eshop.sharedkernel.domain.base.AbstractEntity;
import emt.proekt.eshop.sharedkernel.domain.base.DomainObjectId;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "shops")
public class Shop extends AbstractEntity<ShopId> {

    @Column(name = "shop_name")
    private String shopName;

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

    public Shop(String shopName, String shopBankAccount, String shopUTN, String shopDescription, String shopLogoImage, Category shopCategory) {
        super(DomainObjectId.randomId(ShopId.class));
        this.shopName = shopName;
        this.shopBankAccount = shopBankAccount;
        this.shopUTN = shopUTN;
        this.shopDescription = shopDescription;
        this.shopLogoImage = shopLogoImage;
        this.shopCategory = shopCategory;
    }

    @Override
    public ShopId id() {
        return id;
    }
}
