package emt.proekt.eshop.shopmanagement.domain.model.dto;

import eshop.mk.model.Page;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ShopDetailsDTO {

    private UUID shopId;
    private String shopName;
    private String shopDescription;
    private LocalDateTime createdDate;
    private Long categoryId;
    private URL shopLogoImage;
    private Page<ProductForMainPageDTO> products;
}
