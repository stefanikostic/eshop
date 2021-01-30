package emt.proekt.eshop.productmanagement.domain.repository;

import emt.proekt.eshop.productmanagement.domain.model.ProductImage;
import emt.proekt.eshop.productmanagement.domain.model.ProductImageId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductImagesRepository extends JpaRepository<ProductImage, ProductImageId> {
}
