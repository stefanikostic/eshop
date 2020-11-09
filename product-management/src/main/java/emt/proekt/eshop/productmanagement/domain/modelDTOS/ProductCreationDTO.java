package emt.proekt.eshop.productmanagement.domain.modelDTOS;

import emt.proekt.eshop.sharedkernel.domain.financial.Currency;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ProductCreationDTO {

     String shopId;
     String productName;
     String productDescription;
     Currency currency;
     String productCategoryId;
     List<ProductItemCreationDTO> productItemCreationDTOS;
}
