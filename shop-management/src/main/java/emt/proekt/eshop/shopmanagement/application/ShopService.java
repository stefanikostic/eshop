package emt.proekt.eshop.shopmanagement.application;

import emt.proekt.eshop.sharedkernel.events.ShopCreatedEvent;
import emt.proekt.eshop.shopmanagement.domain.model.Shop;
import emt.proekt.eshop.shopmanagement.domain.model.ShopId;
import emt.proekt.eshop.shopmanagement.domain.model.dto.ShopCreationDTO;
import emt.proekt.eshop.shopmanagement.domain.model.dto.ShopDTO;
import emt.proekt.eshop.shopmanagement.domain.model.exceptions.ShopNotFoundException;
import emt.proekt.eshop.shopmanagement.domain.repository.ShopRepositoryImpl;
import lombok.NonNull;
import lombok.var;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;

import com.google.cloud.storage.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class ShopService {
    private final ShopRepositoryImpl shopRepository;
    private Storage storage;
    private Bucket bucket;

    private final Set<String> contentTypes;

    private final KafkaTemplate<String, ShopCreatedEvent> kafkaTemplate;
    private static final String TOPIC = "createdShopsTopic";

    public ShopService(ShopRepositoryImpl shopRepository, KafkaTemplate<String, ShopCreatedEvent> kafkaTemplate) {
        this.shopRepository = shopRepository;
        this.kafkaTemplate = kafkaTemplate;

        this.contentTypes = new HashSet<>();
        this.contentTypes.add("image/png");
        this.contentTypes.add("image/jpeg");
        this.contentTypes.add("image/jpg");
        storage = StorageOptions.getDefaultInstance().getService();
        bucket = getBucket("eshopmk-78147.appspot.com");

    }

    @Transactional
    public ShopId createShop(ShopCreationDTO shop) {


        var newShop = shopRepository.save(toDomainModel(shop));
        kafkaTemplate.send(TOPIC, new ShopCreatedEvent(Objects.requireNonNull(newShop.id()).getId(), shop.getOwnerId(), LocalDateTime.now().toString()));
        return newShop.id();
    }

    public ShopDTO<ShopId> getShopDetails(String shopId) {
        ShopDTO<ShopId> shop = shopRepository.getShopForDetails(shopId).orElseThrow(ShopNotFoundException::new);

        shop.setShopLogo(downloadShopImage(shop.getShopLogoImage()));
        return shop;
    }

    public emt.proekt.eshop.sharedkernel.domain.base.Page<ShopDTO<ShopId>> getAllShops(String query, int page, int size) {

        org.springframework.data.domain.Page<ShopDTO<ShopId>> result = shopRepository.findAllShops(query, page, size);

        result.getContent().forEach(shopDTO -> {
            URL imageUrl = downloadShopImage(shopDTO.getShopLogoImage());
            shopDTO.setShopLogo(imageUrl);
        });
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

    public void uploadShopImage(MultipartFile image,
                                String shopId) throws IOException, InterruptedException {
        byte[] bytes = image.getBytes();

        Shop shop = shopRepository.findShop(shopId).orElseThrow(ShopNotFoundException::new);

        Blob blob = bucket.create(shop.getName(), bytes, image.getContentType());
        shop.setShopImage(shop.getName());
        shopRepository.save(shop);
    }

    public URL downloadShopImage(String imageBlob) {
        BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of(bucket.getName(), imageBlob)).build();
        URL url = storage.signUrl(blobInfo, 15, TimeUnit.MINUTES, Storage.SignUrlOption.withV4Signature());
        return url;
    }

    private Bucket getBucket(String bucketName) {
        bucket = storage.get(bucketName);
        return bucket;
    }
}