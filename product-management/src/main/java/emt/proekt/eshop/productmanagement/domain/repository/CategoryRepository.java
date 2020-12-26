package emt.proekt.eshop.productmanagement.domain.repository;

import emt.proekt.eshop.productmanagement.domain.model.Category;
import emt.proekt.eshop.productmanagement.domain.model.CategoryId;
import emt.proekt.eshop.productmanagement.domain.modelDTOS.CategoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, CategoryId> {

    Optional<Category> findCategoryById(CategoryId id);

    @Query(value = "WITH recursive cat_tree as (\n" +
            "   select id,\n" +
            "          category_name,\n" +
            "          super_category_id\n" +
            "   from categories\n" +
            "   where id = ?1  -- this defines the start of the recursion\n" +
            "   union all\n" +
            "   select childs.id,\n" +
            "          childs.category_name,\n" +
            "          childs.super_category_id\n" +
            "   from categories as childs\n" +
            "join cat_tree as parent on parent.id = childs.super_category_id \n" +
            "     -- the self join to the CTE builds up the recursion\n" +
            ")\n" +
            "select *\n" +
            "from cat_tree",
            nativeQuery = true)
    List<Category> getCategorySubcategories(String categoryId);

    List<CategoryDTO<CategoryId>> findAllBy();
}
