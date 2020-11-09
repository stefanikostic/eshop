package emt.proekt.eshop.productmanagement.domain.repository;

import emt.proekt.eshop.productmanagement.domain.model.Attribute;
import emt.proekt.eshop.productmanagement.domain.model.AttributeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, AttributeId> {

}
