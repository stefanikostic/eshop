package emt.proekt.eshop.productmanagement.domain.repository;

import emt.proekt.eshop.productmanagement.domain.model.Attribute;
import emt.proekt.eshop.productmanagement.domain.model.AttributeId;
import emt.proekt.eshop.productmanagement.domain.modelDTOS.AttributeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, AttributeId> {

    List<AttributeDTO<AttributeId>> findAllBy();
}
