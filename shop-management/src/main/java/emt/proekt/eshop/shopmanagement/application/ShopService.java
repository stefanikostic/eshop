package emt.proekt.eshop.shopmanagement.application;

import emt.proekt.eshop.shopmanagement.domain.model.Shop;
import emt.proekt.eshop.shopmanagement.domain.model.ShopId;
import emt.proekt.eshop.shopmanagement.domain.model.dto.ShopCreationDTO;
import emt.proekt.eshop.shopmanagement.domain.model.dto.ShopDTO;
import emt.proekt.eshop.shopmanagement.domain.model.exceptions.ShopNotFoundException;
import emt.proekt.eshop.shopmanagement.domain.repository.ShopRepositoryImpl;
import lombok.NonNull;
import lombok.var;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class ShopService {
    private final ShopRepositoryImpl shopRepository;

    public ShopService(ShopRepositoryImpl shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Transactional
    public ShopId createShop(ShopCreationDTO shop) {


        var newShop = shopRepository.save(toDomainModel(shop));
        return newShop.id();
    }

    public ShopDTO<ShopId> getShopDetails(String shopId) {
        ShopDTO<ShopId> shop = shopRepository.getShopForDetails(shopId).orElseThrow(ShopNotFoundException::new);

        //URL imageUrl = imagesService.downloadShopImage(shop.getShopLogoImage());

        return shop;
    }

    public emt.proekt.eshop.sharedkernel.domain.base.Page<ShopDTO<ShopId>> getAllShops(String query, int page, int size) {

        org.springframework.data.domain.Page<ShopDTO<ShopId>> result = shopRepository.findAllShops(query, page, size);

//        result.getContent().forEach(shopDTO -> {
//            URL imageUrl = imagesService.downloadShopImage(shopDTO.getShopLogoImage());
//            shopDTO.setShopLogo(imageUrl);
//        });
        return new emt.proekt.eshop.sharedkernel.domain.base.Page<ShopDTO<ShopId>>(page,
                result.getTotalPages(),
                size,
                result.getTotalElements(),
                result.getContent()) {
        };
    }

    private Shop toDomainModel(@NonNull ShopCreationDTO shop) {
        return new Shop(shop.getShopName(), shop.getShopBankAccount(), shop.getShopUTN(), shop.getShopDescription(), null, LocalDateTime.now());
    }
}