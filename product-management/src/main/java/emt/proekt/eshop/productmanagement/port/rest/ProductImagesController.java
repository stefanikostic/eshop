package emt.proekt.eshop.productmanagement.port.rest;

import emt.proekt.eshop.productmanagement.application.ProductApplicationService;

import emt.proekt.eshop.productmanagement.domain.model.UploadPhotos;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ProductImagesController {

    private final ProductApplicationService productService;

    public ProductImagesController(ProductApplicationService productService) {
        this.productService = productService;
    }

    @PostMapping(path = "/{productId}/uploadImages/{shopName}")
    public ResponseEntity<?> uploadProductImages(@PathVariable String productId,
                                                 @PathVariable String shopName,
                                                 @RequestParam MultipartFile productImage) {
        productService.uploadProductImages(productImage, productId, shopName);

        return ResponseEntity.ok("Upload photos successfully!");

    }

}
