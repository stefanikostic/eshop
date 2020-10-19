package emt.proekt.eshop.shopmanagement.domain.repository;

import emt.proekt.eshop.shopmanagement.domain.model.Category;
import emt.proekt.eshop.shopmanagement.domain.model.CategoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, CategoryId> {
}
