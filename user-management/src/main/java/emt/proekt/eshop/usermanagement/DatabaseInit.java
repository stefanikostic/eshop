package emt.proekt.eshop.usermanagement;

import emt.proekt.eshop.usermanagement.domain.model.Role;
import emt.proekt.eshop.usermanagement.domain.model.User;
import emt.proekt.eshop.usermanagement.domain.repository.RolesRepository;
import emt.proekt.eshop.usermanagement.domain.repository.UsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "app.db-init", havingValue = "true")
public class DatabaseInit implements CommandLineRunner {
    private final RolesRepository rolesRepository;
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;


    public DatabaseInit(
                        RolesRepository rolesRepository,
                        UsersRepository usersRepository,
                        PasswordEncoder passwordEncoder
                        ) {
        this.rolesRepository = rolesRepository;
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... strings) throws Exception {

        this.rolesRepository.deleteAll();
        this.usersRepository.deleteAll();

            Role userRole = new Role("USER");
            Role salesRole = new Role("SALES");
            Role managerRole = new Role("SHOPMANAGER");
            this.rolesRepository.save(userRole);
            this.rolesRepository.save(salesRole);
            this.rolesRepository.save(managerRole);

            User user23 = new User("Aneta", "Ancevska", "aneta.ancevska@gmail.com", passwordEncoder.encode("aneta123"), userRole);
            user23.addRole(managerRole);
            usersRepository.save(user23);
            User user29 = new User( "Leon", "Krstevski","leon.krstevski@gmail.com", passwordEncoder.encode("leon4455"), userRole);
            usersRepository.save(user29);

        System.out.println("DATABASE INITILAIZED");
    }
}