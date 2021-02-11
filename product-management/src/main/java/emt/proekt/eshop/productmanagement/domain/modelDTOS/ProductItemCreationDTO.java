package emt.proekt.eshop.productmanagement.domain.modelDTOS;

import emt.proekt.eshop.productmanagement.domain.model.Attribute;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
public class ProductItemCreationDTO {

    private double price;
    private int quantity;
    private List<Attribute> productItemAttributes;
}
