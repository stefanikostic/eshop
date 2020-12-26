package emt.proekt.eshop.productmanagement.port.rest;

import emt.proekt.eshop.productmanagement.application.ProductApplicationService;
import emt.proekt.eshop.productmanagement.domain.model.AttributeId;
import emt.proekt.eshop.productmanagement.domain.modelDTOS.AttributeDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/attributes")
public class AttributesController {

    private final ProductApplicationService productApplicationService;

    public AttributesController(ProductApplicationService productApplicationService) {
        this.productApplicationService = productApplicationService;
    }

    @GetMapping
    public List<AttributeDTO<AttributeId>> getAllAttributes() {
        return productApplicationService.getAllAttributes();
    }
}
