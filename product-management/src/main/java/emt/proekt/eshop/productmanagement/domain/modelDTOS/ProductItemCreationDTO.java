package emt.proekt.eshop.productmanagement.domain.modelDTOS;

import emt.proekt.eshop.productmanagement.domain.model.Attribute;
import lombok.Data;
import java.util.List;

@Data
public class ProductItemCreationDTO {

    private int price;
    private int quantity;
    private List<Attribute> productItemAttributes;
}
