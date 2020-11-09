package emt.proekt.eshop.shopmanagement.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import emt.proekt.eshop.shopmanagement.domain.model.ShopId;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ShopDTO<ID> {

    @JsonIgnore
    private ID id;
    @Transient
    private String shopId;
    private String shopName;
    private String shopDescription;
    private Long shopCategory;
    private String createdDate;

    @JsonIgnore
    private String shopLogoImage;

    @Transient
    private URL shopLogo;

    public ShopDTO(ID id,
                   String shopName,
                   String shopDescription,
                   String shopLogoImage,
                   String createdDate) {
        this.id = id;
        this.shopDescription = shopDescription;
        this.createdDate = createdDate;
        this.shopName = shopName;
        this.shopLogoImage = shopLogoImage;
    }
}
