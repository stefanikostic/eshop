package emt.proekt.eshop.productmanagement.domain.service;

import emt.proekt.eshop.productmanagement.domain.exceptions.ProductNotSavedException;
import emt.proekt.eshop.productmanagement.domain.model.Attribute;
import emt.proekt.eshop.productmanagement.domain.model.Category;
import emt.proekt.eshop.productmanagement.domain.model.Product;
import emt.proekt.eshop.productmanagement.domain.model.ShopId;
import emt.proekt.eshop.productmanagement.domain.modelDTOS.ProductCreationDTO;
import emt.proekt.eshop.productmanagement.domain.modelDTOS.ProductItemCreationDTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductService {

    private final AttributeService attributeService;

    public ProductService(AttributeService attributeService) {
        this.attributeService = attributeService;
    }

    @Transactional
    public Product createProduct(ProductCreationDTO productCreationDTO, Category category) {

        Product product = new Product(productCreationDTO.getProductName(), false, productCreationDTO.getProductDescription(), new ShopId(productCreationDTO.getShopId()), category);

        int minPrice = productCreationDTO.getProductItemCreationDTOS().stream().mapToInt(ProductItemCreationDTO::getPrice).min().getAsInt();
        product.setPrice(minPrice, productCreationDTO.getCurrency());

        List<Attribute> allAttributes = attributeService.getAllAttributes();

        productCreationDTO.getProductItemCreationDTOS().forEach(productItemDto -> {

            Set<Attribute> productItemAttributes = new HashSet<>();

            for (Attribute productItemAttribute : productItemDto.getProductItemAttributes()) {
                boolean notExists = true;
                boolean newAttr = false;
                String dtoAttrName = productItemAttribute.getAttributeName();
                String dtoAttrValue = productItemAttribute.getAttributeValue();

                if (dtoAttrName != null && dtoAttrValue != null) {
                    for (Attribute allAttribute : allAttributes) {
                        String attributeName = allAttribute.getAttributeName();
                        String attributeValue = allAttribute.getAttributeValue();

                        if (attributeName.equals(dtoAttrName)) {
                            newAttr = true;
                            notExists = false;

                            if (attributeValue.equals(dtoAttrValue)) {
                                productItemAttributes.add(allAttribute);
                                newAttr = false;
                                break;
                            }
                        }
                    }
                }

                if (newAttr) {
                    Attribute attribute = attributeService.save(new Attribute(dtoAttrName, dtoAttrValue));
                    productItemAttributes.add(attribute);
                }

                if (notExists) {
                    throw new ProductNotSavedException();
                }
            }
            product.addProductItem(productItemDto, productItemAttributes);
        });
        return product;
    }
}