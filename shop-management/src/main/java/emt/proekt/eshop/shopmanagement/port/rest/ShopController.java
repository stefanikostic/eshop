package emt.proekt.eshop.shopmanagement.port.rest;

import emt.proekt.eshop.sharedkernel.domain.base.Page;
import emt.proekt.eshop.shopmanagement.application.ShopService;
import emt.proekt.eshop.shopmanagement.domain.model.ShopId;
import emt.proekt.eshop.shopmanagement.domain.model.dto.ShopCreationDTO;
import emt.proekt.eshop.shopmanagement.domain.model.dto.ShopDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ShopController {
    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @PostMapping(path = "/management/create")
    public ShopId createShop(@RequestBody ShopCreationDTO shop) {

        return shopService.createShop(shop);
    }

    @GetMapping(path = "/public/{shopId}")
    public ShopDTO<ShopId> getShop(@PathVariable String shopId) {
        return shopService.getShopDetails(shopId);
    }

    @GetMapping(path = "/public/allShops")
    public Page<ShopDTO<ShopId>> getAllShops(@RequestParam(name = "q", defaultValue = "", required = false) String query,
                                             @RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                             @RequestParam(name = "page-size", defaultValue = "20", required = false) int size) {
        return shopService.getAllShops(query, page, size);
    }

    @PostMapping(path = "management/{shopId}/uploadImage")
    public ResponseEntity<?> uploadShopImage(@PathVariable String shopId,
                                             @RequestParam MultipartFile shopLogoImage) throws IOException, InterruptedException {

        shopService.uploadShopImage(shopLogoImage, shopId);
        return ResponseEntity.ok("Uploaded photo successfully!");

    }
}
