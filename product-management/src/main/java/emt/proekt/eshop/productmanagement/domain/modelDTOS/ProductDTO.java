package emt.proekt.eshop.productmanagement.domain.modelDTOS;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
public class ProductDTO {

    private UUID productId;
    private String productName;
    private String productDescription;
    private Double price;
    private String imagePath;

    public ProductDTO(UUID productId,
                      String productName,
                      String productDescription,
                      Double price,
                      String imagePath) {

        this.productName = productName;
        this.imagePath = imagePath;
        this.productId = productId;
        this.productDescription = productDescription;
        this.price = price;
    }
}
