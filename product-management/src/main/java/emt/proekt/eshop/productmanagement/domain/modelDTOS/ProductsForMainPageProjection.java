package emt.proekt.eshop.productmanagement.domain.modelDTOS;

import emt.proekt.eshop.sharedkernel.domain.financial.Currency;

import java.util.UUID;

public interface ProductsForMainPageProjection {

     String getProductId();
     String getProductName();
     String getProductDescription();
     Double getPrice();
     Currency getCurrency();
     String getImagePath();
     String getShopId();
}
