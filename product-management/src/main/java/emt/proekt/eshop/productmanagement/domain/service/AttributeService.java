package emt.proekt.eshop.productmanagement.domain.service;

import emt.proekt.eshop.productmanagement.domain.model.Attribute;
import emt.proekt.eshop.productmanagement.domain.repository.AttributeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttributeService {

    private final AttributeRepository attributeRepository;

    public AttributeService(AttributeRepository attributeRepository) {
        this.attributeRepository = attributeRepository;
    }

    public List<Attribute> getAllAttributes(){
        return attributeRepository.findAll();
    }

    public Attribute save(Attribute attribute) {
        return attributeRepository.save(attribute);
    }
}
