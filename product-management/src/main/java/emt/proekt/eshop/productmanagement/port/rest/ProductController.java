package emt.proekt.eshop.productmanagement.port.rest;

import emt.proekt.eshop.productmanagement.application.ProductApplicationService;
import emt.proekt.eshop.productmanagement.domain.model.Product;
import emt.proekt.eshop.productmanagement.domain.model.ProductId;
import emt.proekt.eshop.productmanagement.domain.modelDTOS.ProductCreationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

/*    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") String productId) {
        return productService.findById(new ProductId(productId))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }*/
}
