package emt.proekt.eshop.shopmanagement;

import emt.proekt.eshop.shopmanagement.domain.model.Shop;
import emt.proekt.eshop.shopmanagement.domain.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@ConditionalOnProperty(name = "app.db-init")
@RequiredArgsConstructor
public class DatabaseInit implements CommandLineRunner {

    private final ShopRepository shopsRepository;

    @Override
    public void run (String... args) throws Exception {
        Shop shop1 = shopsRepository.save(new Shop("TestShop1","5dflror","dejdiejeE","This is a description for this shop","11111111111", LocalDateTime.now()));
        Shop shop2 = shopsRepository.save(new Shop("TestShop2","5dfcddlror","dejfffediejeE","This is a description for this shop","11111111111", LocalDateTime.now()));
        Shop shop3 = shopsRepository.save(new Shop("TestShop1","5dfddflror","dejdfdssaiejeE","This is a description for this shop","11111111111", LocalDateTime.now()));

    }
}
