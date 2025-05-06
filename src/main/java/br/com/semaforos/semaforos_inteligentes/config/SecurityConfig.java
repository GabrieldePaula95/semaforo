package br.com.semaforos.semaforos_inteligentes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // só se for API REST
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/usuarios").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/usuarios").permitAll()
//                        .requestMatchers(HttpMethod.PUT, "/usuarios").permitAll()
//                        .requestMatchers(HttpMethod.DELETE, "/usuarios/**").permitAll() // ou .hasRole("ADMIN") // ou .hasRole("ADMIN")
//                        .anyRequest().authenticated()
//                )
                .httpBasic(Customizer.withDefaults()); // usa Basic Auth

        return http.build();
    }
}
