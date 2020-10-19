package emt.proekt.eshop.shopmanagement.domain.repository;

import emt.proekt.eshop.shopmanagement.domain.model.Shop;
import emt.proekt.eshop.shopmanagement.domain.model.ShopId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop, ShopId> {
}
