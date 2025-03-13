package com.example.immobiliSpring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf ->csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/auth/register",
                                "/auth/login",
                                "/annessi/getAllAnnessi",
                                "/annessi/search",
                                "/annessi/insertAnnessi",
                                "/annessi/updateAnnessi/{id}",
                                "/annessi/deleteAnnessi/{id}",
                                "/immobile/getAllImmobili",
                                "/immobile/search",
                                "/immobile/insertImmobile",
                                "/immobile/updateImmobile/{id}",
                                "/immobile/deleteImmobileById/{id}",
                                "/proprietari/getAllProprietari",
                                "/proprietari/search",
                                "/proprietari/insertProp",
                                "/proprietari/updatePropById/{id}",
                                "/proprietari/deletePropById/{id}"
                        )
                        .permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}
