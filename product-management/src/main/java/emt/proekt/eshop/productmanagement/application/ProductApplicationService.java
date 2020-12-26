package emt.proekt.eshop.productmanagement.application;

import emt.proekt.eshop.productmanagement.domain.exceptions.CategoryNotFoundException;
import emt.proekt.eshop.productmanagement.domain.exceptions.ProductNotFoundException;
import emt.proekt.eshop.productmanagement.domain.exceptions.ProductNotSavedException;
import emt.proekt.eshop.productmanagement.domain.model.*;
import emt.proekt.eshop.productmanagement.domain.modelDTOS.*;
import emt.proekt.eshop.productmanagement.domain.repository.AttributeRepository;
import emt.proekt.eshop.productmanagement.domain.repository.CategoryRepository;
import emt.proekt.eshop.productmanagement.domain.repository.ProductItemRepository;
import emt.proekt.eshop.productmanagement.domain.repository.ProductRepository;
import emt.proekt.eshop.productmanagement.domain.service.ProductService;
import emt.proekt.eshop.sharedkernel.domain.base.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductApplicationService {

    private final ProductService productService;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final AttributeRepository attributeRepository;
    private final ProductItemRepository productItemRepository;

    public ProductApplicationService(ProductService productService, ProductRepository productRepository, CategoryRepository categoryRepository, AttributeRepository attributeRepository, ProductItemRepository productItemRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.attributeRepository = attributeRepository;
        this.productItemRepository = productItemRepository;
    }

    @Transactional
    public String createProduct(ProductCreationDTO productCreationDTO) {
        Category category = categoryRepository.findCategoryById(new CategoryId(productCreationDTO.getProductCategoryId())).orElseThrow(CategoryNotFoundException::new);

        try {
            Product product = productService.createProduct(productCreationDTO, category);
            productRepository.save(product);
            return product.getId().getId();
        } catch (Exception ex) {
            throw new ProductNotSavedException();
        }
    }

    public List<CategoryDTO<CategoryId>> getAllCategories() {
        try {
            return categoryRepository.findAllBy();
        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }

    public List<AttributeDTO<AttributeId>> getAllAttributes() {
        try {
            return attributeRepository.findAllBy();
        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }

    public Page<ProductForMainPageDTO> getProducts(int page,
                                                   int size,
                                                   String sort,
                                                   String order,
                                                   String categoryId) {
        List<ProductForMainPageDTO> productsDTO;
        org.springframework.data.domain.Page<ProductsForMainPageProjection> result;
        if (!categoryId.equals("-1")) {
            List<Category> categorySubcategories = categoryRepository.getCategorySubcategories(categoryId);
            List<String> categories = categorySubcategories.parallelStream().map(c -> c.getId().getId()).collect(Collectors.toList());
            result = findAllProductForMainPageByCategory(page, size, sort, order, categories);
        } else {
            result = findAllProductForMainPage(page, size, sort, order);
        }
        productsDTO = this.createProductForMainPageDTO(result.getContent());
        return new Page<>(page,
                result.getTotalPages(),
                size,
                result.getTotalElements(),
                productsDTO);
    }

    private List<ProductForMainPageDTO> createProductForMainPageDTO(List<ProductsForMainPageProjection> products) {

        return products.stream().map(productsProjection -> {
            //  URL imageUrl = productImagesService.downloadProductImage(productsProjection.getImagePath());
            return new ProductForMainPageDTO(productsProjection.getProductId(), productsProjection.getProductName(), productsProjection.getProductDescription(), productsProjection.getPrice(), productsProjection.getCurrency(), productsProjection.getShopId());
        }).collect(Collectors.toList());
    }

    public org.springframework.data.domain.Page<ProductsForMainPageProjection> findAllProductForMainPageByCategory(int page, int size, String sort, String order, List<String> categorySubcategories) {
        if (order.equals("DESC")) {
            return productRepository.findAllProductForMainPageByCategory(false, categorySubcategories, PageRequest.of(page, size, Sort.by(sort).descending()));
        } else if (order.equals("ASC")) {
            return productRepository.findAllProductForMainPageByCategory(false, categorySubcategories, PageRequest.of(page, size, Sort.by(sort).ascending()));
        } else {
            throw new ProductNotFoundException();
        }
    }


    public org.springframework.data.domain.Page<ProductsForMainPageProjection> findAllProductForMainPage(int page, int size, String sort, String order) {
        if (order.equals("DESC")) {
            return productRepository.findAllProductForMainPage(false, PageRequest.of(page, size, Sort.by(sort).descending()));
        } else if (order.equals("ASC")) {
            return productRepository.findAllProductForMainPage(false, PageRequest.of(page, size, Sort.by(sort).ascending()));
        } else {
            throw new ProductNotFoundException();
        }
    }

    public ProductDetailsDTO getProduct(String productId) {

        List<ProductDTO<ProductId>> productDTO = productRepository.findProductByProductId(new ProductId(productId));

        if (productDTO.size() == 0) {
            throw new ProductNotFoundException();
        }
        List<ProductItem> productItems = productItemRepository.findAllByProductAndDeletedFalse(productDTO.get(0).getProductId());

        // List<String> imagePaths = productDTO.parallelStream().map(ProductDTO::getImagePath).collect(Collectors.toList());
        List<URL> productImages = new ArrayList<>(); //productImagesService.getProductImages(imagePaths);
        List<ProductReviewDTO> productReviews = new ArrayList<>(); //productReviewService.findAllByProductId(productId);
        return new ProductDetailsDTO(productDTO.get(0).getProductId(), productDTO.get(0).getProductName(), productDTO.get(0).getProductDescription(), productDTO.get(0).getPrice(), productDTO.get(0).getCurrency(), productImages, productItems, productReviews);
    }


    public Page<ProductForMainPageDTO> getProductsFromShop(int page,
                                                           int size,
                                                           String sort,
                                                           String order,
                                                           String shopId) {

        org.springframework.data.domain.Page<ProductsForMainPageProjection> result = findAllProductFromShop(page, size, sort, order, shopId);
        List<ProductForMainPageDTO> productsDTO = this.createProductForMainPageDTO(result.getContent());

        return new Page<>(page,
                result.getTotalPages(),
                size,
                result.getTotalElements(),
                productsDTO);
    }


    public Page<ProductForMainPageDTO> getProductsFromShopByCategory(int page,
                                                                     int size,
                                                                     String sort,
                                                                     String order,
                                                                     String categoryId,
                                                                     String shopId) {

        org.springframework.data.domain.Page<ProductsForMainPageProjection> result;
        List<Category> categorySubcategories = categoryRepository.getCategorySubcategories(categoryId);
        List<String> categories = categorySubcategories.parallelStream().map(c -> c.getId().getId()).collect(Collectors.toList());
        result = findAllProductFromShopByCategory(page, size, sort, order, categories, shopId);
        List<ProductForMainPageDTO> productsDTO = this.createProductForMainPageDTO(result.getContent());

        return new Page<>(page,
                result.getTotalPages(),
                size,
                result.getTotalElements(),
                productsDTO);
    }

    private org.springframework.data.domain.Page<ProductsForMainPageProjection> findAllProductFromShop(int page, int size, String sort, String order, String shopId) {

        if (order.equals("DESC")) {
            return productRepository.findAllProductFromShop(false, shopId, PageRequest.of(page, size, Sort.by(sort).descending()));
        } else if (order.equals("ASC")) {
            return productRepository.findAllProductFromShop(false, shopId, PageRequest.of(page, size, Sort.by(sort).ascending()));
        } else {
            throw new ProductNotFoundException();
        }
    }

    private org.springframework.data.domain.Page<ProductsForMainPageProjection> findAllProductFromShopByCategory(int page, int size, String sort, String order, List<String> categorySubcategories, String shopId) {

        if (order.equals("DESC")) {
            return productRepository.findAllProductFromShopByCategory(false, categorySubcategories, shopId, PageRequest.of(page, size, Sort.by(sort).descending()));
        } else if (order.equals("ASC")) {
            return productRepository.findAllProductFromShopByCategory(false, categorySubcategories, shopId, PageRequest.of(page, size, Sort.by(sort).ascending()));
        } else {
            throw new ProductNotFoundException();
        }
    }

}
