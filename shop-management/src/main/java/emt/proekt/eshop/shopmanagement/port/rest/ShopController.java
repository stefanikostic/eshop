package emt.proekt.eshop.shopmanagement.port.rest;

import emt.proekt.eshop.shopmanagement.application.ShopService;
import emt.proekt.eshop.shopmanagement.domain.model.Shop;
import emt.proekt.eshop.shopmanagement.domain.model.ShopId;
import emt.proekt.eshop.shopmanagement.domain.model.dto.ShopCreationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/shops")
public class ShopController {
    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @PostMapping(path = "/create")
    @PreAuthorize("hasRole('ROLE_USER') && !hasAnyRole('ROLE_SHOPMANAGER', 'ROLE_SALES')")
    public ShopId createShop(@RequestHeader String userId,
                           @RequestBody ShopCreationDTO shop) {
        return shopService.createShop(userId, shop);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shop> findById(@PathVariable("id") String shopId) {
        return shopService.findById(new ShopId(shopId))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Shop> findAll() {
        return shopService.findAll();
    }
}
