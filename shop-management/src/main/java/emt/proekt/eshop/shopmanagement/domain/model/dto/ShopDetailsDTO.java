package emt.proekt.eshop.shopmanagement.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ShopDetailsDTO {

    private String shopId;
    private String shopName;
    private String shopDescription;
    private String createdDate;
    private Long categoryId;
   // private URL shopLogoImage;
}
