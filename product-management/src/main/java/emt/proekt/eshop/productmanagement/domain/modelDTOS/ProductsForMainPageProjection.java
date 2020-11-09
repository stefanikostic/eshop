package emt.proekt.eshop.productmanagement.domain.modelDTOS;

import java.util.UUID;

public interface ProductsForMainPageProjection {

     UUID getProductId();
     String getProductName();
     String getProductDescription();
     Double getPrice();
     String getImagePath();
     UUID getShopId();
}
