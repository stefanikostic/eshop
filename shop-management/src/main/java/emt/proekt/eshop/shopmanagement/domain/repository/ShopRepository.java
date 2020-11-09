package emt.proekt.eshop.shopmanagement.domain.repository;

import emt.proekt.eshop.shopmanagement.domain.model.Shop;
import emt.proekt.eshop.shopmanagement.domain.model.ShopId;
import emt.proekt.eshop.shopmanagement.domain.model.dto.ShopDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop, ShopId> {
    @Query("select new emt.proekt.eshop.shopmanagement.domain.model.dto.ShopDTO(s.id,s.name,s.shopDescription,s.shopLogoImage,s.createdDate) from Shop s where s.id=:shopId")
    Optional<ShopDTO<ShopId>> findByShopId(String shopId);

    @Query("select new emt.proekt.eshop.shopmanagement.domain.model.dto.ShopDTO(s.id,s.name,s.shopDescription,s.shopLogoImage,s.createdDate) from Shop s")
    org.springframework.data.domain.Page<ShopDTO<ShopId>> findAllBy(Pageable pageable);

    @Query("select new emt.proekt.eshop.shopmanagement.domain.model.dto.ShopDTO(s.id,s.name,s.shopDescription,s.shopLogoImage,s.createdDate) from Shop s where s.name like concat('%', :query , '%')")
    org.springframework.data.domain.Page<ShopDTO<ShopId>> findShopsBy(String query, Pageable pageable);

}
