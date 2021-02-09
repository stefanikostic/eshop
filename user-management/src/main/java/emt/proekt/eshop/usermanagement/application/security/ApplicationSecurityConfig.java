package emt.proekt.eshop.usermanagement.application.security;

import emt.proekt.eshop.usermanagement.application.UsersService;
import emt.proekt.eshop.usermanagement.application.jwt.JwtAuthenticationEntryPoint;
import emt.proekt.eshop.usermanagement.application.jwt.JwtConfig;
import emt.proekt.eshop.usermanagement.application.jwt.JwtTokenVerifier;
import emt.proekt.eshop.usermanagement.application.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //Za avtorizacija so anotaacii
@RequiredArgsConstructor
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private final PasswordEncoder passwordEncoder;

    private final UsersService usersService;

    @Bean
    public JwtTokenVerifier authenticationJwtTokenFilter() {
        return new JwtTokenVerifier();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/auth/signin","/login", "/api/users/createUser", "/shops/public/**", "/products/**", "/products/categories/**", "/products/attributes/**").permitAll()
                .antMatchers("/shops/management/create").access("hasRole('USER') and !hasAnyRole('SHOPMANAGER','SALES')")
                .antMatchers("/shops/management/{shopId}/uploadImage").hasRole("USER")

                .antMatchers("products/{productId}/uploadImages").hasAnyRole("SHOPMANAGER", "SALES")
                .antMatchers("/carts/**").hasRole("USER")
                .antMatchers("/products/management/create").hasAnyRole("SHOPMANAGER", "SALES")
                .anyRequest()
                .authenticated();
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(usersService);
        return provider;
    }
}