package emt.proekt.eshop.cartmanagement.domain.model;

import emt.proekt.eshop.sharedkernel.domain.financial.Price;
import lombok.Getter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.HashSet;
import java.util.Set;

@Getter
public class ProductItem {

    private ProductItemId productItemId;

    private String name;

    private Price price;

    private int quantity;
}
