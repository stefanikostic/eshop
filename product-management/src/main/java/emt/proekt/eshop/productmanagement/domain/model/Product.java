package emt.proekt.eshop.productmanagement.domain.model;

import emt.proekt.eshop.productmanagement.domain.modelDTOS.ProductItemCreationDTO;
import emt.proekt.eshop.sharedkernel.domain.base.AbstractEntity;
import emt.proekt.eshop.sharedkernel.domain.base.DomainObjectId;
import emt.proekt.eshop.sharedkernel.domain.financial.Currency;
import emt.proekt.eshop.sharedkernel.domain.financial.Price;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.var;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Table(name = "products")
public class Product extends AbstractEntity<ProductId> {
    @Column(name = "product_name", nullable = false)
    private String name;

    @Column(name = "deleted", nullable = false)
    private boolean deleted;

    @Column(name = "product_description")
    private String productDescription;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "shop_id", nullable = false))
    private ShopId shopId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "price", column = @Column(name = "price")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency"))
    })
    private Price price;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="productId", referencedColumnName="id")
    private Set<ProductItem> productItems = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="productId", referencedColumnName="id")
    private Set<ProductImage> productImages = new HashSet<>();


    @ManyToOne
    private Category category;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    public Product(String name, boolean deleted, String productDescription, ShopId shopId, Category category) {
        super(DomainObjectId.randomId(ProductId.class));
        this.name = name;
        this.deleted = deleted;
        this.productDescription = productDescription;
        this.shopId = shopId;
        this.category = category;
        this.createdDate = LocalDateTime.now();
    }

    public Product () {

    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void setProductDescription(String description) {
        this.productDescription = description;
    }

    public void setProductName(String name) {
        this.name = name;
    }

    public void setPrice(int price, Currency currency) {
        this.price = new Price(price, currency);
    }

    public void setProductCategory(Category category) {
        this.category = category;
    }


    public ProductItem addProductItem(@NonNull ProductItemCreationDTO productItemDTO, Set<Attribute> productItemAttributes) {
        Objects.requireNonNull(productItemDTO, "product must not be null");
        var item = new ProductItem(false, new Price(productItemDTO.getPrice(), Currency.EUR), productItemDTO.getQuantity(), productItemAttributes, id);
        productItems.add(item);
        return item;
    }
}
