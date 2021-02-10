package emt.proekt.eshop.productmanagement.application;

import com.google.cloud.storage.*;
import emt.proekt.eshop.productmanagement.domain.exceptions.*;
import emt.proekt.eshop.productmanagement.domain.model.*;
import emt.proekt.eshop.productmanagement.domain.modelDTOS.*;
import emt.proekt.eshop.productmanagement.domain.repository.*;
import emt.proekt.eshop.productmanagement.domain.service.ProductService;
import emt.proekt.eshop.sharedkernel.domain.base.Page;
import emt.proekt.eshop.sharedkernel.events.CartItemRemovedEvent;
import emt.proekt.eshop.sharedkernel.events.ShopCreatedEvent;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class ProductApplicationService {

    private final ProductService productService;
    private final ProductImagesRepository productImagesRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final AttributeRepository attributeRepository;
    private final ProductItemRepository productItemRepository;
    private Storage storage;
    private Bucket bucket;
    private int imageId;

    private static Semaphore imageSemaphore;
    private final Set<String> contentTypes;

    public ProductApplicationService(ProductService productService, ProductImagesRepository productImagesRepository, ProductRepository productRepository, CategoryRepository categoryRepository, AttributeRepository attributeRepository, ProductItemRepository productItemRepository) {
        this.productService = productService;
        this.productImagesRepository = productImagesRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.attributeRepository = attributeRepository;
        this.productItemRepository = productItemRepository;

        this.contentTypes = new HashSet<>();
        this.contentTypes.add("image/png");
        this.contentTypes.add("image/jpeg");
        this.contentTypes.add("image/jpg");
        storage = StorageOptions.getDefaultInstance().getService();
        bucket = getBucket("eshopmk-78147.appspot.com");
        imageId = 0;
        imageSemaphore = new Semaphore(1);
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
            URL imageUrl = downloadProductImage(productsProjection.getImagePath());
            return new ProductForMainPageDTO(productsProjection.getProductId(), productsProjection.getProductName(), productsProjection.getProductDescription(), productsProjection.getPrice(), productsProjection.getCurrency(), imageUrl, productsProjection.getShopId());
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

        List<String> imagePaths = productDTO.parallelStream().map(ProductDTO::getImagePath).collect(Collectors.toList());
        List<URL> productImages = getProductImages(imagePaths);
        List<ProductReviewDTO> productReviews = new ArrayList<>(); //productReviewService.findAllByProductId(productId);
        return new ProductDetailsDTO(productDTO.get(0).getProductId(), productDTO.get(0).getProductName(), productDTO.get(0).getProductDescription(), productDTO.get(0).getPrice(), productDTO.get(0).getCurrency(), productImages, productItems, productReviews);
    }

    private List<URL> getProductImages(List<String> productImagesPaths) {
        return productImagesPaths.parallelStream().map(this::downloadProductImage).collect(Collectors.toList());
    }

    private URL downloadProductImage(String imageBlob) {
        //TODO CHANGE IN imageBlob
        BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of(bucket.getName(), imageBlob)).build();
        return storage.signUrl(blobInfo, 15, TimeUnit.MINUTES, Storage.SignUrlOption.withV4Signature());
    }

    // Check for bucket existence and create if needed.
    private Bucket getBucket(String bucketName) {
        bucket = storage.get(bucketName);
        return bucket;
    }

    public void uploadOneProductImage(MultipartFile image,
                                      Product product,
                                      String shopName) throws IOException, InterruptedException {

        byte[] bytes = image.getBytes();
        System.out.println("uploadOneProductImage");

        String productName = product.getName();

        String imagePath = shopName + "_" + productName + "_" + 1;


        ProductImage productImage = new ProductImage(imagePath, product.id());
        Blob blob = bucket.create(imagePath, bytes, image.getContentType());

        productImagesRepository.save(productImage);
    }

    public void uploadProductImages(MultipartFile productImage,
                                    String productId,
                                    String shopName) {
        if (productImage != null) {
            Product product = productRepository.findProductByIdAndDeleted(new ProductId(productId), false);
            if (product == null) {
                throw new ProductImagesNotSavedException();
            }
            String imageContentType = productImage.getContentType();
            boolean notMatch = true;
            for (String cType : contentTypes) {
                if (cType.equals(imageContentType)) {
                    notMatch = false;
                    break;
                }
            }
            if (notMatch) {
                throw new ProductImagesNotSavedException();
            }

            try {
                uploadOneProductImage(productImage, product, shopName);
            } catch (IOException | InterruptedException e) {
                throw new ProductImagesNotSavedException();
            }
        } else {
            throw new ProductImagesNotSavedException();
        }
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


    @Transactional
    public void decrementProductItemsQty(CartItemRequestDTO cartItem) {
        try {
            ProductItem productItem = productItemRepository.findByIdAndDeletedFalse(new ProductItemId(cartItem.getProductItemId())).orElseThrow(ProductItemNotFoundException::new);

            productItem.subtractQuantity(cartItem.getCartItemQuantity());
            productItemRepository.save(productItem);

        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    @KafkaListener(topics = "removedCartItemsTopic", groupId = "group_id",
            containerFactory = "cartItemRemovedListenerFactory")
    @org.springframework.transaction.annotation.Transactional
    public void onCartItemRemoved(CartItemRemovedEvent cartItemRemovedEvent) {
        System.out.println("Consumed JSON Message: " + cartItemRemovedEvent);

        try {
            ProductItem productItem = productItemRepository.findByIdAndDeletedFalse(new ProductItemId(cartItemRemovedEvent.getProductItemId())).orElseThrow(ProductItemNotFoundException::new);
            productItem.addQuantity(cartItemRemovedEvent.getQuantity());
            productItemRepository.save(productItem);

        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }
}
