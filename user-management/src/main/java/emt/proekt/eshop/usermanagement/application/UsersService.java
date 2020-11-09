package emt.proekt.eshop.usermanagement.application;

import emt.proekt.eshop.usermanagement.application.security.UserDetailsImpl;
import emt.proekt.eshop.usermanagement.domain.dtos.UserEmailsProjection;
import emt.proekt.eshop.usermanagement.domain.exceptions.UserEmailAlreadyExistsException;
import emt.proekt.eshop.usermanagement.domain.exceptions.UserNotFoundException;
import emt.proekt.eshop.usermanagement.domain.exceptions.UserTableNotSavedException;
import emt.proekt.eshop.usermanagement.domain.model.Role;
import emt.proekt.eshop.usermanagement.domain.model.User;
import emt.proekt.eshop.usermanagement.domain.repository.RolesRepository;
import emt.proekt.eshop.usermanagement.domain.repository.UsersRepository;
import emt.proekt.eshop.usermanagement.integration.ShopCreatedEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;

@Service
public class UsersService implements UserDetailsService {

    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;

    public UsersService(UsersRepository usersRepository,
                        RolesRepository rolesRepository,
                        PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    public User createUser(User user) throws UserEmailAlreadyExistsException, UserTableNotSavedException {

        List<UserEmailsProjection> emails = usersRepository.findAllBy(); //Get emails from all users
        boolean emailExists = emails.stream().anyMatch(userProjection -> userProjection.getEmail().equals(user.getEmail()));

        if (emailExists) {
            throw new UserEmailAlreadyExistsException();
        }

        Role role = rolesRepository.findByRoleName("USER");
        if (role == null) {
            throw new UserTableNotSavedException();
        }

        User savedUser;
        try {
            savedUser = usersRepository.save(new User(user.getFirstName(), user.getLastName(), user.getEmail(), passwordEncoder.encode(user.getPassword()), role));
        } catch (Exception ex) {
            throw new UserTableNotSavedException();
        }
        return savedUser;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = usersRepository
                .findUserByEmail(s)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format("Username %s not found", s))
                );
        return new UserDetailsImpl(user);
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onShopCreated(ShopCreatedEvent event) {
        User u = usersRepository.findById(event.getUserId()).orElseThrow(UserNotFoundException::new);
        u.setShopId(event.getShopId());
        u.addRole(this.rolesRepository.findByRoleName("SHOPMANAGER"));
        usersRepository.save(u);
    }
}