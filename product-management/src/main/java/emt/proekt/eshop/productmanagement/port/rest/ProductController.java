package emt.proekt.eshop.productmanagement.port.rest;

import emt.proekt.eshop.productmanagement.application.ProductService;
import emt.proekt.eshop.productmanagement.domain.model.Product;
import emt.proekt.eshop.productmanagement.domain.model.ProductId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") String productId) {
        return productService.findById(new ProductId(productId))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }
}
