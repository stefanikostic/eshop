package emt.proekt.eshop.usermanagement;

import emt.proekt.eshop.sharedkernel.SharedConfiguration;
import emt.proekt.eshop.usermanagement.application.SimpleFilter;
import emt.proekt.eshop.usermanagement.application.jwt.JwtConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableZuulProxy //act as Zuul proxy
@SpringBootApplication
@EnableJpaRepositories
@EntityScan
@EnableConfigurationProperties(JwtConfig.class)
@Import(SharedConfiguration.class)
public class UserManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserManagementApplication.class, args);
    }
    @Bean
    public SimpleFilter simpleFilter() {
        return new SimpleFilter();
    }
}
