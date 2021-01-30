package emt.proekt.eshop.productmanagement.port.rest;

import emt.proekt.eshop.productmanagement.application.ProductApplicationService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ProductImagesController {

    private final ProductApplicationService productService;

    public ProductImagesController(ProductApplicationService productService) {
        this.productService = productService;
    }

    @PostMapping(path = "/{productId}/uploadImages")
    public ResponseEntity<?> uploadProductImages(@PathVariable String productId,
                                                 @RequestParam MultipartFile[] productImages,
                                                 @RequestParam String shopName) {
        productService.uploadProductImages(productImages, productId, shopName);

        return ResponseEntity.ok("Upload photos successfully!");

    }

}
