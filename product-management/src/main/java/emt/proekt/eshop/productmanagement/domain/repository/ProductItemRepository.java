package emt.proekt.eshop.productmanagement.domain.repository;

import emt.proekt.eshop.productmanagement.domain.model.ProductItem;
import emt.proekt.eshop.productmanagement.domain.model.ProductItemId;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductItemRepository extends JpaRepository<ProductItem, ProductItemId> {

    @EntityGraph(attributePaths = "attributes")
    @Query("select pI from ProductItem pI where pI.product=:product")
    List<ProductItem> findAllByProductAndDeletedFalse(String product);

    Optional<ProductItem> findByIdAndDeletedFalse(ProductItemId productItemId);
}
