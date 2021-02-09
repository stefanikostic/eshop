package emt.proekt.eshop.productmanagement.port.rest;

import emt.proekt.eshop.productmanagement.application.ProductApplicationService;
import emt.proekt.eshop.productmanagement.domain.model.CategoryId;
import emt.proekt.eshop.productmanagement.domain.modelDTOS.CategoryDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesController {

    private final ProductApplicationService productApplicationService;

    public CategoriesController(ProductApplicationService productApplicationService) {
        this.productApplicationService = productApplicationService;
    }

    @GetMapping
    public List<CategoryDTO<CategoryId>> getAllCategories() {
        return productApplicationService.getAllCategories();
    }
}
