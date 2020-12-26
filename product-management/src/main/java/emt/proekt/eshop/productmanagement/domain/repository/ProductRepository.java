package emt.proekt.eshop.productmanagement.domain.repository;

import emt.proekt.eshop.productmanagement.domain.model.Product;
import emt.proekt.eshop.productmanagement.domain.model.ProductId;
import emt.proekt.eshop.productmanagement.domain.modelDTOS.ProductDTO;
import emt.proekt.eshop.productmanagement.domain.modelDTOS.ProductsForMainPageProjection;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, ProductId> {

//pI.image_path as ImagePath,
    //product_images as pI join
    //on p.product_id = pI.product_product_id
    //and pI.image_path like '%1'

    //join product_images on products.product_id = product_images.product_product_id
    //and product_images.image_path like '%1'
    @Query(value = "select  p.id as ProductId, p.product_name as ProductName,p.price as Price,p.currency as Currency, p.product_description as ProductDescription,p.shop_id  as ShopId from products as p where p.category_id in ?2 and p.deleted = ?1",
            countQuery = "select count(*) from products where products.category_id in ?2 and products.deleted = ?1 ",
            nativeQuery = true)
    org.springframework.data.domain.Page<ProductsForMainPageProjection> findAllProductForMainPageByCategory(Boolean deleted, List<String> categorySubcategories, Pageable pageable);

//pI.image_path as ImagePath
    //from product_images as pI join products as p on p.product_id = pI.product_product_id
    // pI.image_path like '%1' and

    //join product_images on products.product_id = product_images.product_product_id
    //product_images.image_path like '%1' and
    @Query(value = "select  p.id as ProductId, p.product_name as ProductName,p.price as Price, p.currency as Currency, p.product_description as ProductDescription,p.shop_id as ShopId from products as p where p.deleted = ?1 ",
            countQuery = "select count(*) from products where products.deleted = :deleted ",
            nativeQuery = true)
    org.springframework.data.domain.Page<ProductsForMainPageProjection> findAllProductForMainPage(Boolean deleted, Pageable pageable);


    //join p.productImages as pI on p.productId=pI.product.productId
    //pI.imagePath
    @Query("select new emt.proekt.eshop.productmanagement.domain.modelDTOS.ProductDTO(p.id,p.name,p.productDescription,p.price.price,p.price.currency) from Product p where p.id=:productId and p.deleted=false")
    List<ProductDTO<ProductId>> findProductByProductId(ProductId productId);


//and pI.image_path like '%1'
    //,pI.image_path as ImagePath
    //left join product_images as pI on p.id = pI.product_id

    //and product_images.image_path like '%1'
    //left join product_images on products.id = product_images.product_id
    @Query(value = "select p.id as ProductId, p.product_name as ProductName,p.price as Price, p.currency as Currency, p.product_description as ProductDescription,p.shop_id  as ShopId from products as p where p.category_id in ?2  and p.shop_id=?3 and p.deleted = ?1",
            countQuery = "select count(*) from products where products.category_id in ?2 and products.shop_id=?3 and products.deleted = ?1 ",
            nativeQuery = true)
    org.springframework.data.domain.Page<ProductsForMainPageProjection> findAllProductFromShopByCategory(Boolean deleted, List<String> categorySubcategories, String shopId, Pageable pageable);



    //pI.image_path as ImagePath,
    //left join product_images as pI on p.id = pI.product_id
    //and pI.image_path like '%1'

    //left join product_images on products.id = product_images.product_id
    //product_images.image_path like '%1' and
    @Query(value = "select  p.id as ProductId, p.product_name as ProductName,p.price as Price, p.currency as Currency, p.product_description as ProductDescription,p.shop_id  as ShopId from products as p where p.shop_id=?2 and p.deleted = ?1 ",
            countQuery = "select count(*) from products where products.shop_id=?2 and products.deleted = ?1 ",
            nativeQuery = true)
    org.springframework.data.domain.Page<ProductsForMainPageProjection> findAllProductFromShop(Boolean deleted, String shopId, Pageable pageable);

}
