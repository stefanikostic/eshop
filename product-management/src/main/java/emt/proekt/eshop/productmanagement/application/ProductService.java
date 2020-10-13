package emt.proekt.eshop.productmanagement.application;

import emt.proekt.eshop.productmanagement.domain.model.Product;
import emt.proekt.eshop.productmanagement.domain.model.ProductId;
import emt.proekt.eshop.productmanagement.domain.repository.ProductRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @NonNull
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @NonNull
    public Optional<Product> findById(@NonNull ProductId productId) {
        Objects.requireNonNull(productId, "productId must not be null");
        return productRepository.findById(productId);
    }

    public void deleteProduct(@NonNull ProductId productId){
        Objects.requireNonNull(productId, "productId must not be null");
        productRepository.deleteById(productId);
    }

    @NonNull
    public Product saveProduct(@NonNull Product product){
        Objects.requireNonNull(product, "product must not be null");
        return productRepository.save(product);
    }
}
