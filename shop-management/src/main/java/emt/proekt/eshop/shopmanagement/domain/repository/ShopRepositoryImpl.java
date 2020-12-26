package emt.proekt.eshop.shopmanagement.domain.repository;

import emt.proekt.eshop.shopmanagement.domain.model.Shop;
import emt.proekt.eshop.shopmanagement.domain.model.ShopId;
import emt.proekt.eshop.shopmanagement.domain.model.dto.ShopDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class ShopRepositoryImpl {
    private final ShopRepository shopRepository;

    public ShopRepositoryImpl(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public Optional<ShopDTO<ShopId>> getShopForDetails(String shopId){
        return shopRepository.findShopById(new ShopId(shopId));
    }

    public Shop save(Shop shop){
        return shopRepository.saveAndFlush(shop);


    }
    public org.springframework.data.domain.Page<ShopDTO<ShopId>> findAllShops(String query, int page,int size){
        if (!query.equals("")){
            return shopRepository.findShopsBy(query, PageRequest.of(page,size));
        }
        return shopRepository.findAllBy(PageRequest.of(page,size));

    }
}
