package emt.proekt.eshop.productmanagement.domain.modelDTOS;

import emt.proekt.eshop.productmanagement.domain.model.ProductItem;
import emt.proekt.eshop.sharedkernel.domain.financial.Currency;
import lombok.Data;

import java.net.URL;
import java.util.List;
import java.util.UUID;

@Data
public class ProductDetailsDTO {
    private String productId;
    private String productName;
    private String productDescription;
    private int price;
    private Currency currency;
    private List<URL> imagesUrls;
    private List<ProductItem> productItems;
    private List<ProductReviewDTO> productReviews;

    public ProductDetailsDTO(String productId,
                             String productName,
                             String productDescription,
                             int price,
                             Currency currency,
                             List<URL> imagesUrls,
                             List<ProductItem> productItems,
                             List<ProductReviewDTO> productReviews) {

        this.productName = productName;
        this.imagesUrls = imagesUrls;
        this.productId = productId;
        this.productDescription = productDescription;
        this.price = price;
        this.currency = currency;
        this.productItems = productItems;
        this.productReviews = productReviews;
    }
}
