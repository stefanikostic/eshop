package emt.proekt.eshop.usermanagement.domain.repository;

import emt.proekt.eshop.usermanagement.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Role, Integer> {

    Role findByRoleName(String name);
}