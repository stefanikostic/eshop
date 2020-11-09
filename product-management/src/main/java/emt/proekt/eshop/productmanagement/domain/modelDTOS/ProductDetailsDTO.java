package emt.proekt.eshop.productmanagement.domain.modelDTOS;

import emt.proekt.eshop.productmanagement.domain.model.ProductItem;
import lombok.Data;

import java.net.URL;
import java.util.List;
import java.util.UUID;

@Data
public class ProductDetailsDTO {
    private UUID productId;
    private String productName;
    private String productDescription;
    private Double price;
    private List<URL> imagesUrls;
    private List<ProductItem> productItems;
    private List<ProductReviewDTO> productReviews;

    public ProductDetailsDTO(UUID productId,
                             String productName,
                             String productDescription,
                             Double price,
                             List<URL> imagesUrls,
                             List<ProductItem> productItems,
                             List<ProductReviewDTO> productReviews) {

        this.productName = productName;
        this.imagesUrls = imagesUrls;
        this.productId = productId;
        this.productDescription = productDescription;
        this.price = price;
        this.productItems = productItems;
        this.productReviews = productReviews;
    }
}
