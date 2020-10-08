package emt.proekt.eshop.usermanagement.application;

import emt.proekt.eshop.productmanagement.domain.model.Product;
import emt.proekt.eshop.productmanagement.domain.model.ProductId;
import emt.proekt.eshop.usermanagement.domain.model.User;
import emt.proekt.eshop.usermanagement.domain.model.UserId;
import emt.proekt.eshop.usermanagement.domain.repository.UserRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @NonNull
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @NonNull
    public Optional<User> findById(@NonNull UserId userId) {
        Objects.requireNonNull(userId, "userId must not be null");
        return userRepository.findById(userId);
    }

    public void deleteUser(@NonNull UserId userId){
        Objects.requireNonNull(userId, "userId must not be null");
        userRepository.deleteById(userId);
    }

    @NonNull
    public User saveUser(@NonNull User user){
        Objects.requireNonNull(user, "user must not be null");
        return userRepository.save(user);
    }
}
