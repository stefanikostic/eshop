package emt.proekt.eshop.usermanagement.port.rest;

import emt.proekt.eshop.usermanagement.application.UsersService;
import emt.proekt.eshop.usermanagement.application.jwt.JwtResponse;
import emt.proekt.eshop.usermanagement.application.jwt.JwtTokenVerifier;
import emt.proekt.eshop.usermanagement.application.jwt.JwtUtils;
import emt.proekt.eshop.usermanagement.application.jwt.LoginRequest;
import emt.proekt.eshop.usermanagement.application.security.UserDetailsImpl;
import emt.proekt.eshop.usermanagement.domain.repository.RolesRepository;
import emt.proekt.eshop.usermanagement.domain.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UsersService usersService;
    private final RolesRepository rolesRepository;
    private final JwtUtils jwtUtils;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) usersService.loadUserByUsername(loginRequest.getUsername());
        List<GrantedAuthority> roles = new ArrayList<>(userDetails.getAuthorities());
        logger.debug("" + userDetails.getUsername());
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getUser().id(),
                userDetails.getUsername(),
                userDetails.getUser().getEmail(),
                roles));
    }

}
