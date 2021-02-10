package emt.proekt.eshop.productmanagement.domain.model;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class UploadPhotos {

    MultipartFile[] productImages;
    String shopName;
}
