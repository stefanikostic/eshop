package emt.proekt.eshop.productmanagement.domain.modelDTOS;

import emt.proekt.eshop.sharedkernel.domain.base.DomainObjectId;
import emt.proekt.eshop.sharedkernel.domain.financial.Currency;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
public class ProductDTO<ID extends DomainObjectId> {

    private String productId;
    private String productName;
    private String productDescription;
    private double price;
    private Currency currency;
    private String imagePath;

    public ProductDTO(ID productId,
                      String productName,
                      String productDescription,
                      double price,
                      Currency currency,
                      String imagePath
                      ) {

        this.productName = productName;
        this.imagePath = imagePath;
        this.productId = productId.getId();
        this.productDescription = productDescription;
        this.price = price;
        this.currency = currency;
    }
}
