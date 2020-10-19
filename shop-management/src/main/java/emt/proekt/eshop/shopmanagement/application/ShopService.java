package emt.proekt.eshop.shopmanagement.application;

import emt.proekt.eshop.shopmanagement.domain.event.ShopCreated;
import emt.proekt.eshop.shopmanagement.domain.model.*;
import emt.proekt.eshop.shopmanagement.domain.model.dto.ShopCreationDTO;
import emt.proekt.eshop.shopmanagement.domain.model.exceptions.CategoryNotFoundException;
import emt.proekt.eshop.shopmanagement.domain.repository.CategoryRepository;
import emt.proekt.eshop.shopmanagement.domain.repository.ShopRepository;
import lombok.NonNull;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class ShopService {
    private final ShopRepository shopRepository;

    private final CategoryRepository categoryRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final Validator validator;

    public ShopService(ShopRepository shopRepository, CategoryRepository categoryRepository, ApplicationEventPublisher applicationEventPublisher, Validator validator) {
        this.shopRepository = shopRepository;
        this.categoryRepository = categoryRepository;
        this.applicationEventPublisher = applicationEventPublisher;
        this.validator = validator;
    }

    @NonNull
    public List<Shop> findAll() {
        return shopRepository.findAll();
    }

    @NonNull
    public Optional<Shop> findById(@NonNull ShopId shopId) {
        Objects.requireNonNull(shopId, "shopId must not be null");
        return shopRepository.findById(shopId);
    }

    public void deleteShop(@NonNull ShopId shopId){
        Objects.requireNonNull(shopId, "shopId must not be null");
        shopRepository.deleteById(shopId);
    }

    @NonNull
    public Shop saveShop(@NonNull Shop shop){
        Objects.requireNonNull(shop, "shop must not be null");
        return shopRepository.save(shop);
    }

    @Transactional
    public ShopId createShop(@NonNull String userId, @NonNull ShopCreationDTO shop){
        Objects.requireNonNull(shop,"shop must not be null");
        var newShop = shopRepository.saveAndFlush(toDomainModel(shop));
        applicationEventPublisher.publishEvent(new ShopCreated(newShop.id(), new UserId(userId), Instant.now()));
        return newShop.id();
    }

    private Shop toDomainModel(@NonNull ShopCreationDTO shop) {
        Category category = this.categoryRepository.findById(new CategoryId(shop.getShopCategory())).orElseThrow(CategoryNotFoundException::new);
        return new Shop(shop.getShopName(), shop.getShopBankAccount(), shop.getShopUTN(), shop.getShopDescription(), null, category);
    }
}