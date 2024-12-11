package com.sr.ecommerce_g1.Configuration;

import com.sr.ecommerce_g1.Service.G1UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    G1UserDetailsService service;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception{

        security.authorizeHttpRequests( request ->
                request.requestMatchers("/auth/**","/demo").permitAll()
                        .anyRequest().authenticated()
        ).csrf(customizer->customizer.disable())
                .sessionManagement
                        (
                                sec ->
                        sec.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        )
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
        ;


       return security.build();

    }

    @Bean
    public AuthenticationProvider provider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(service);
        return provider;
    }

    @Bean
    public AuthenticationManager manager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }



}
