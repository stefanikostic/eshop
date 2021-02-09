package emt.proekt.eshop.productmanagement;

import emt.proekt.eshop.productmanagement.domain.model.*;
import emt.proekt.eshop.productmanagement.domain.modelDTOS.ProductItemCreationDTO;
import emt.proekt.eshop.productmanagement.domain.repository.AttributeRepository;
import emt.proekt.eshop.productmanagement.domain.repository.CategoryRepository;
import emt.proekt.eshop.productmanagement.domain.repository.ProductItemRepository;
import emt.proekt.eshop.productmanagement.domain.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ConditionalOnProperty(name = "app.db-init")
public class DatabaseInit implements CommandLineRunner {
    private final AttributeRepository attributeRepository;
    private final CategoryRepository categoriesRepository;
    private final ProductItemRepository productItemRepository;

    public DatabaseInit(AttributeRepository attributeRepository, CategoryRepository categoriesRepository, ProductItemRepository productItemRepository) {
        this.attributeRepository = attributeRepository;
        this.categoriesRepository = categoriesRepository;
        this.productItemRepository = productItemRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.attributeRepository.deleteAll();
        this.categoriesRepository.deleteAll();
        this.productItemRepository.deleteAll();

        List<Attribute> attributes = new ArrayList<>();
        if (this.attributeRepository.count() == 0) {
            attributes.add(new Attribute("SIZE_EU", "XS"));
            attributes.add(new Attribute("SIZE_EU", "S"));
            attributes.add(new Attribute("SIZE_EU", "M"));
            attributes.add(new Attribute("SIZE_EU", "L"));
            attributes.add(new Attribute("SIZE_EU", "XL"));
            attributes.add(new Attribute("SIZE_EU", "XXL"));
            attributes.add(new Attribute("SIZE_EU", "XXXL"));

            attributes.add(new Attribute("SIZE_SHOES", "20"));
            attributes.add(new Attribute("SIZE_SHOES", "22"));
            attributes.add(new Attribute("SIZE_SHOES", "24"));
            attributes.add(new Attribute("SIZE_SHOES", "26"));
            attributes.add(new Attribute("SIZE_SHOES", "28"));
            attributes.add(new Attribute("SIZE_SHOES", "30"));
            attributes.add(new Attribute("SIZE_SHOES", "34"));
            attributes.add(new Attribute("SIZE_SHOES", "36"));
            attributes.add(new Attribute("SIZE_SHOES", "38"));
            attributes.add(new Attribute("SIZE_SHOES", "40"));
            attributes.add(new Attribute("SIZE_SHOES", "41"));
            attributes.add(new Attribute("SIZE_SHOES", "42"));
            attributes.add(new Attribute("SIZE_SHOES", "43"));
            attributes.add(new Attribute("SIZE_SHOES", "44"));
            attributes.add(new Attribute("SIZE_SHOES", "45"));

            attributes.add(new Attribute("WAIST_EU", "XS"));
            attributes.add(new Attribute("WAIST_EU", "S"));
            attributes.add(new Attribute("WAIST_EU", "M"));
            attributes.add(new Attribute("WAIST_EU", "L"));
            attributes.add(new Attribute("WAIST_EU", "XL"));
            attributes.add(new Attribute("WAIST_EU", "XXL"));
            attributes.add(new Attribute("WAIST_EU", "XXXL"));

            attributes.add(new Attribute("COLOR", "BLUE"));
            attributes.add(new Attribute("COLOR", "RED"));
            attributes.add(new Attribute("COLOR", "WHITE"));
            attributes.add(new Attribute("COLOR", "BLACK"));
            attributes.add(new Attribute("COLOR", "GREEN"));
            attributes.add(new Attribute("COLOR", "YELLOW"));
            attributes.add(new Attribute("COLOR", "PINK"));
            attributes.add(new Attribute("COLOR", "PURPLE"));
            attributes.add(new Attribute("COLOR", "ORANGE"));
            attributes.add(new Attribute("COLOR", "GRAY"));
            attributes.add(new Attribute("COLOR", "BROWN"));

            attributes.add(new Attribute("MATERIAL", "GOLD"));
            attributes.add(new Attribute("MATERIAL", "BRONZE"));
            attributes.add(new Attribute("MATERIAL", "SILVER"));
            attributes.add(new Attribute("MATERIAL", "LEATHER"));
            attributes.add(new Attribute("MATERIAL", "COTTON"));
            attributes.add(new Attribute("MATERIAL", "WOOL"));
            attributes.add(new Attribute("MATERIAL", "SILK"));
            attributes.add(new Attribute("MATERIAL", "POLYPROPYLENE"));
            attributes.add(new Attribute("MATERIAL", "PVC"));

            attributes.add(new Attribute("DIAMETER_INCHES", "1"));
            attributes.add(new Attribute("DIAMETER_CENTIMETERS", "1"));
            attributes.add(new Attribute("DIAMETER_METERS", "1"));
            attributes.add(new Attribute("DIAMETER_MILLIMETERS", "1"));

            attributes.add(new Attribute("LENGTH_CENTIMETERS", "1"));
            attributes.add(new Attribute("LENGTH_CENTIMETERS", "20"));
            attributes.add(new Attribute("LENGTH_CENTIMETERS", "40"));
            attributes.add(new Attribute("LENGTH_CENTIMETERS", "60"));

            attributes.add(new Attribute("LENGTH_METERS", "1"));
            attributes.add(new Attribute("LENGTH_MILLIMETERS", "1"));
            attributes.add(new Attribute("LENGTH_INCHES", "1"));

            attributes.add(new Attribute("WEIGHT_GRAMS", "1"));
            attributes.add(new Attribute("WEIGHT_KILOS", "1"));

            this.attributeRepository.saveAll(attributes);

        }

        if (this.categoriesRepository.count() == 0) {

            Category categoryClothes = new Category("Clothing&Shoes", new CategoryId(null));
            this.categoriesRepository.save(categoryClothes);

            Category categoryMenClothes = new Category("Men", categoryClothes.getId());
            this.categoriesRepository.save(categoryMenClothes);

            Category categoryMenTshirts = new Category("T-Shirts", categoryMenClothes.getId());
            this.categoriesRepository.save(categoryMenTshirts);

            Category categoryMenShirts = new Category("Shirts", categoryMenClothes.getId());
            this.categoriesRepository.save(categoryMenShirts);

            Category categoryMenJeans = new Category("Jeans", categoryMenClothes.getId());
            this.categoriesRepository.save(categoryMenJeans);

            Category categoryMenHoodies = new Category("Hoodies", categoryMenClothes.getId());
            this.categoriesRepository.save(categoryMenHoodies);

            Category categoryMenJackets = new Category("Jackets", categoryMenClothes.getId());
            this.categoriesRepository.save(categoryMenJackets);

            Category categoryMenShoes = new Category("Shoes", categoryMenClothes.getId());
            this.categoriesRepository.save(categoryMenShoes);

            Category categoryWomenClothes = new Category("Women", categoryClothes.getId());
            this.categoriesRepository.save(categoryWomenClothes);

            Category categoryWomenTshirts = new Category("T-Shirts", categoryWomenClothes.getId());
            this.categoriesRepository.save(categoryWomenTshirts);

            Category categoryWomenShirts = new Category("Shirts", categoryWomenClothes.getId());
            this.categoriesRepository.save(categoryWomenShirts);

            Category categoryWomenJeans = new Category("Jeans", categoryWomenClothes.getId());
            this.categoriesRepository.save(categoryWomenJeans);

            Category categoryWomenHoodies = new Category("Hoodies", categoryWomenClothes.getId());
            this.categoriesRepository.save(categoryWomenHoodies);

            Category categoryWomenJackets = new Category("Jackets", categoryWomenClothes.getId());
            this.categoriesRepository.save(categoryWomenJackets);

            Category categoryWomenShoes = new Category("Shoes", categoryWomenClothes.getId());
            this.categoriesRepository.save(categoryWomenShoes);

            Category categoryKidsClothes = new Category("Kids", categoryClothes.getId());
            this.categoriesRepository.save(categoryKidsClothes);

            Category categoryKidsTshirts = new Category("T-Shirts", categoryKidsClothes.getId());
            this.categoriesRepository.save(categoryKidsTshirts);

            Category categoryKidsShirts = new Category("Shirts", categoryKidsClothes.getId());
            this.categoriesRepository.save(categoryKidsShirts);

            Category categoryKidsJeans = new Category("Jeans", categoryKidsClothes.getId());
            this.categoriesRepository.save(categoryKidsJeans);

            Category categoryKidsHoodies = new Category("Hoodies", categoryKidsClothes.getId());
            this.categoriesRepository.save(categoryKidsHoodies);

            Category categoryKidsJackets = new Category("Jackets", categoryKidsClothes.getId());
            this.categoriesRepository.save(categoryKidsJackets);

            Category categoryKidsShoes = new Category("Shoes", categoryKidsClothes.getId());
            this.categoriesRepository.save(categoryKidsShoes);

            Category categoryAccessories = new Category("Accessories",  new CategoryId(null));
            this.categoriesRepository.save(categoryAccessories);

            Category categoryWallets = new Category("Wallets", categoryAccessories.getId());
            this.categoriesRepository.save(categoryWallets);

            Category categorySunglasses = new Category("Sunglasses", categoryAccessories.getId());
            this.categoriesRepository.save(categorySunglasses);

            Category categoryBags = new Category("Bags", categoryAccessories.getId());
            this.categoriesRepository.save(categoryBags);

            Category categoryJewellery = new Category("Jewellery",  new CategoryId(null));
            this.categoriesRepository.save(categoryJewellery);

            Category categoryRings = new Category("Rings", categoryJewellery.getId());
            this.categoriesRepository.save(categoryRings);

            Category categoryEarrings = new Category("Earrings", categoryJewellery.getId());
            this.categoriesRepository.save(categoryEarrings);

            Category categoryNecklace = new Category("Necklace", categoryJewellery.getId());
            this.categoriesRepository.save(categoryNecklace);

            Category categoryBracelet = new Category("Bracelet", categoryJewellery.getId());
            this.categoriesRepository.save(categoryBracelet);

            Category categoryHome = new Category("Home",  new CategoryId(null));
            this.categoriesRepository.save(categoryHome);

            Category categoryFurniture = new Category("Furniture", categoryHome.getId());
            this.categoriesRepository.save(categoryFurniture);

            Category categoryRugs = new Category("Rugs", categoryHome.getId());
            this.categoriesRepository.save(categoryRugs);

            Category categoryBedding = new Category("Bedding", categoryHome.getId());
            this.categoriesRepository.save(categoryBedding);

            Category categoryBlankets = new Category("Blankets", categoryBedding.getId());
            this.categoriesRepository.save(categoryBlankets);

            Category categoryPillows = new Category("Pillows", categoryBedding.getId());
            this.categoriesRepository.save(categoryPillows);

            Category categoryWallDecor = new Category("WallDecor", categoryHome.getId());
            this.categoriesRepository.save(categoryWallDecor);

            Category categoryLightning = new Category("Lightning", categoryHome.getId());
            this.categoriesRepository.save(categoryLightning);

        }
        System.out.println("----DATABASE INITIALIZED----");
    }
}