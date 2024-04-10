package com.example.FoodDeivery.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(securedEnabled = true)

public class SecurityConfig {
    // Configure password encoder bean
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // Configure user details service bean
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder){
        UserDetails customer=User.withUsername("customer").password(encoder.encode("custom")).roles("CUSTOMER").build();
        UserDetails restaurant=User.withUsername("restaurant").password(encoder.encode("rest")).roles("RESTAURANT").build();
        UserDetails admin=User.withUsername("admin").password(encoder.encode("admin")).roles("ADMIN").build();
        UserDetails deliveryPerson=User.withUsername("DeliveryPerson").password(encoder.encode("delivery")).roles("DELIVERYPERSON").build();
        return new InMemoryUserDetailsManager(admin,restaurant,customer,deliveryPerson);
    }

    // Configure security filter chain bean
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.csrf(AbstractHttpConfigurer::disable)
        .formLogin(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry.anyRequest().authenticated())
        .httpBasic(withDefaults())
        .build();
        
    }
}