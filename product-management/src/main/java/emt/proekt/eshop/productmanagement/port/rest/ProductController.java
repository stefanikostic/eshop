package emt.proekt.eshop.productmanagement.port.rest;

import emt.proekt.eshop.productmanagement.application.ProductApplicationService;
import emt.proekt.eshop.productmanagement.domain.model.Product;
import emt.proekt.eshop.productmanagement.domain.model.ProductId;
import emt.proekt.eshop.productmanagement.domain.modelDTOS.ProductCreationDTO;
import emt.proekt.eshop.productmanagement.domain.modelDTOS.ProductDetailsDTO;
import emt.proekt.eshop.productmanagement.domain.modelDTOS.ProductForMainPageDTO;
import emt.proekt.eshop.sharedkernel.domain.base.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ProductController {
    private final ProductApplicationService productService;

    public ProductController(ProductApplicationService productService) {
        this.productService = productService;
    }

    @PostMapping(path = "/management/create")
    public String createProduct(@RequestBody ProductCreationDTO productCreationDTO) {
        return productService.createProduct(productCreationDTO);
    }

    @GetMapping(path = "/all")
    public Page<ProductForMainPageDTO> getProductsByCategory(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                                             @RequestParam(name = "page-size", defaultValue = "10", required = false) int size,
                                                             @RequestParam(name = "sortBy", defaultValue = "created_date", required = false) String sort,
                                                             @RequestParam(name = "order", defaultValue = "DESC", required = false) String order,
                                                             @RequestParam(name = "categoryId", defaultValue = "-1", required = false) String categoryId) {
        return productService.getProducts(page, size, sort, order, categoryId);
    }

    @GetMapping(path = "/{product}")
    public ProductDetailsDTO getProduct(@PathVariable(name = "product") String productId) {
        return productService.getProduct(productId);
    }

    @GetMapping(path = "/shop/{shopId}")
    public Page<ProductForMainPageDTO> getProductsForShop(@PathVariable(name = "shopId") String shopId,
                                                          @RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                                          @RequestParam(name = "page-size", defaultValue = "10", required = false) int size,
                                                          @RequestParam(name = "sortBy", defaultValue = "created_date", required = false) String sort,
                                                          @RequestParam(name = "order", defaultValue = "DESC", required = false) String order,
                                                          @RequestParam(name = "categoryId", required = false) String categoryId) {
        if (categoryId != null) {
            return productService.getProductsFromShopByCategory(page, size, sort, order, categoryId, shopId);
        } else {
            return productService.getProductsFromShop(page, size, sort, order, shopId);
        }
    }
}
