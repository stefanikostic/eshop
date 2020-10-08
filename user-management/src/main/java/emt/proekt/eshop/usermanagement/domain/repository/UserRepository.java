package emt.proekt.eshop.usermanagement.domain.repository;

import emt.proekt.eshop.usermanagement.domain.model.User;
import emt.proekt.eshop.usermanagement.domain.model.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UserId> {
}
