package emt.proekt.eshop.usermanagement.domain.repository;

import emt.proekt.eshop.usermanagement.domain.dtos.UserEmailsProjection;
import emt.proekt.eshop.usermanagement.domain.model.User;
import emt.proekt.eshop.usermanagement.domain.model.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, UserId> {

    List<UserEmailsProjection> findAllBy();

    //Optional<User> findByUserIdAndShop(UUID userId, UUID shop);

    Optional<User> findUserByEmail(String email);
}
