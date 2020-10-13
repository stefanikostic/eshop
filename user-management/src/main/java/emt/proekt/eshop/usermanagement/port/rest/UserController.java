package emt.proekt.eshop.usermanagement.port.rest;

import emt.proekt.eshop.usermanagement.application.UsersService;
import emt.proekt.eshop.usermanagement.domain.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UsersService usersService;

    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping(path = "/createUser")
    public User createUserAccount(@RequestBody User user) {
        return usersService.createUser(user);
    }
}
