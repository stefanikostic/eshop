package emt.proekt.eshop.productmanagement.domain.repository;

import emt.proekt.eshop.productmanagement.domain.model.Product;
import emt.proekt.eshop.productmanagement.domain.model.ProductId;
import emt.proekt.eshop.productmanagement.domain.modelDTOS.ProductDTO;
import emt.proekt.eshop.productmanagement.domain.modelDTOS.ProductsForMainPageProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, ProductId> {

    @Query(value = "select  p.id as ProductId, p.product_name as ProductName,p.price as Price,p.currency as Currency, p.product_description as ProductDescription,p.shop_id  as ShopId, pI.image_path as ImagePath from products as p left outer join product_images as pI on p.id=pI.product_id where p.category_id in ?2 and p.deleted = ?1 and pI.image_path like '%1'",
            countQuery = "select count(*) from products  where products.category_id in ?2 and products.deleted = ?1 ",
            nativeQuery = true)
    org.springframework.data.domain.Page<ProductsForMainPageProjection> findAllProductForMainPageByCategory(Boolean deleted, List<String> categorySubcategories, Pageable pageable);


    @Query(value = "select  p.id as ProductId, p.product_name as ProductName,p.price as Price, p.currency as Currency, p.product_description as ProductDescription,p.shop_id as ShopId,pI.image_path as ImagePath from products as p left outer join product_images as pI on p.id=pI.product_id where p.deleted = ?1 and pI.image_path like '%1'",
            countQuery = "select count(*) from products where products.deleted = :deleted ",
            nativeQuery = true)
    org.springframework.data.domain.Page<ProductsForMainPageProjection> findAllProductForMainPage(Boolean deleted, Pageable pageable);


    @Query("select new emt.proekt.eshop.productmanagement.domain.modelDTOS.ProductDTO(p.id,p.name,p.productDescription,p.price.price,p.price.currency, pI.imagePath) from Product p left outer join ProductImage as pI on p.id=pI.product where p.id=:productId and p.deleted=false")
    List<ProductDTO<ProductId>> findProductByProductId(ProductId productId);

    Product findProductByIdAndDeleted(ProductId productID, Boolean deleted);

    @Query(value = "select p.id as ProductId, p.product_name as ProductName,p.price as Price, p.currency as Currency, p.product_description as ProductDescription,p.shop_id as ShopId,pI.image_path as ImagePath from products as p left outer join product_images as pI on p.id=pI.product_id where p.category_id in ?2  and p.shop_id=?3 and p.deleted = ?1 and pI.image_path like '%1'",
            countQuery = "select count(*) from products where products.category_id in ?2 and products.shop_id=?3 and products.deleted = ?1 ",
            nativeQuery = true)
    org.springframework.data.domain.Page<ProductsForMainPageProjection> findAllProductFromShopByCategory(Boolean deleted, List<String> categorySubcategories, String shopId, Pageable pageable);

    @Query(value = "select  p.id as ProductId, p.product_name as ProductName,p.price as Price, p.currency as Currency, p.product_description as ProductDescription,p.shop_id  as ShopId, pI.image_path as ImagePath from products as p left outer join product_images as pI on p.id=pI.product_id where p.shop_id=?2 and p.deleted = ?1 and pI.image_path like '%1'",
            countQuery = "select count(*) from products where products.shop_id=?2 and products.deleted = ?1 ",
            nativeQuery = true)
    org.springframework.data.domain.Page<ProductsForMainPageProjection> findAllProductFromShop(Boolean deleted, String shopId, Pageable pageable);

}
