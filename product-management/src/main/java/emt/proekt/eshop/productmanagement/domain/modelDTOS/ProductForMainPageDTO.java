package emt.proekt.eshop.productmanagement.domain.modelDTOS;

import emt.proekt.eshop.sharedkernel.domain.financial.Currency;
import lombok.Data;

import java.net.URL;
import java.util.UUID;

@Data
public class ProductForMainPageDTO {

    private String productId;
    private String productName;
    private String productDescription;
    private Double price;
   // private URL imageURL;
    private String shopId;
    private Currency currency;

    public ProductForMainPageDTO(String productId,
                                 String productName,
                                 String productDescription,
                                 Double price,
                                 Currency currency,
                              //   URL imageURL,
                                 String shopId) {
        this.productName = productName;
        this.productId = productId;
        this.productDescription = productDescription;
        this.price = price;
        //this.imageURL = imageURL;
        this.shopId = shopId;
        this.currency = currency;
    }
}
