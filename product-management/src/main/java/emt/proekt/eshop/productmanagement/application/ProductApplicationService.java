package emt.proekt.eshop.productmanagement.application;

import emt.proekt.eshop.productmanagement.domain.exceptions.CategoryNotFoundException;
import emt.proekt.eshop.productmanagement.domain.exceptions.ProductNotSavedException;
import emt.proekt.eshop.productmanagement.domain.model.Category;
import emt.proekt.eshop.productmanagement.domain.model.CategoryId;
import emt.proekt.eshop.productmanagement.domain.model.Product;
import emt.proekt.eshop.productmanagement.domain.modelDTOS.*;
import emt.proekt.eshop.productmanagement.domain.repository.CategoryRepository;
import emt.proekt.eshop.productmanagement.domain.repository.ProductRepository;
import emt.proekt.eshop.productmanagement.domain.service.ProductService;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
public class ProductApplicationService {

    private final ProductService productService;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductApplicationService(ProductService productService, ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public String createProduct(ProductCreationDTO productCreationDTO) {
        Category category = categoryRepository.findById(new CategoryId(productCreationDTO.getProductCategoryId())).orElseThrow(CategoryNotFoundException::new);

        Product product = productService.createProduct(productCreationDTO, category);
        try {
            productRepository.save(product);
        } catch (Exception ex){
            throw new ProductNotSavedException();
        }
        return product.getId().getId();
    }

  /*  @Override
    public Page<ProductForMainPageDTO> getProducts(int page,
                                                   int size,
                                                   String sort,
                                                   String order,
                                                   Long categoryId) {
        List<ProductForMainPageDTO> productsDTO;
        org.springframework.data.domain.Page<ProductsForMainPageProjection> result;
        if (categoryId != -1) {
            List<Category> categorySubcategories = categoryService.getCategorySubcategories(categoryId);
            List<Long> categories = categorySubcategories.parallelStream().map(Category::getCategoryId).collect(Collectors.toList());
            result = productRepository.findAllProductForMainPageByCategory(page, size, sort, order, categories);
            productsDTO = this.createProductForMainPageDTO(result.getContent());
        } else {
            result = productRepository.findAllProductForMainPage(page, size, sort, order);
            productsDTO = this.createProductForMainPageDTO(result.getContent());
        }
        return new Page<>(page,
                result.getTotalPages(),
                size,
                result.getTotalElements(),
                productsDTO);
    }

    private List<ProductForMainPageDTO> createProductForMainPageDTO(List<ProductsForMainPageProjection> products) {

        return products.stream().map(productsProjection -> {
            URL imageUrl = productImagesService.downloadProductImage(productsProjection.getImagePath());
            return new ProductForMainPageDTO(productsProjection.getProductId(), productsProjection.getProductName(), productsProjection.getProductDescription(), productsProjection.getPrice(), imageUrl, productsProjection.getShopId());
        }).collect(Collectors.toList());
    }

    @Override
    public ProductDetailsDTO getProduct(UUID productId) {


        List<ProductDTO> productDTO = productRepository.findProductByProductId(productId);

        if (productDTO.size() == 0) {
            throw new ProductNotFoundException();
        }

        UUID productUUID = productDTO.get(0).getProductId();
        List<String> imagePaths = productDTO.parallelStream().map(ProductDTO::getImagePath).collect(Collectors.toList());
        List<URL> productImages = productImagesService.getProductImages(imagePaths);
        List<ProductItem> productItems = productItemService.getProductItems(productUUID);
        List<ProductReviewDTO> productReviews = productReviewService.findAllByProductId(productId);
        ProductDetailsDTO productDetailsDTO = new ProductDetailsDTO(productDTO.get(0).getProductId(), productDTO.get(0).getProductName(), productDTO.get(0).getProductDescription(), productDTO.get(0).getPrice(), productImages, productItems, productReviews);
        return productDetailsDTO;
    }

    //Get Products from Shop
    @Override
    public Page<ProductForMainPageDTO> getProductsFromShop(int page,
                                                           int size,
                                                           String sort,
                                                           String order,
                                                           UUID shopId) {

        org.springframework.data.domain.Page<ProductsForMainPageProjection> result = productRepository.findAllProductFromShop(page, size, sort, order, shopId);
        List<ProductForMainPageDTO> productsDTO = this.createProductForMainPageDTO(result.getContent());

        return new Page<>(page,
                result.getTotalPages(),
                size,
                result.getTotalElements(),
                productsDTO);
    }

    @Override
    public Page<ProductForMainPageDTO> getProductsFromShopByCategory(int page,
                                                                     int size,
                                                                     String sort,
                                                                     String order,
                                                                     Long categoryId,
                                                                     UUID shopId) {

        org.springframework.data.domain.Page<ProductsForMainPageProjection> result;
        List<Category> categorySubcategories = categoryService.getCategorySubcategories(categoryId);
        List<Long> categories = categorySubcategories.parallelStream().map(Category::getCategoryId).collect(Collectors.toList());
        result = productRepository.findAllProductFromShopByCategory(page, size, sort, order, categories, shopId);
        List<ProductForMainPageDTO> productsDTO = this.createProductForMainPageDTO(result.getContent());

        return new Page<>(page,
                result.getTotalPages(),
                size,
                result.getTotalElements(),
                productsDTO);
    }*/

}
