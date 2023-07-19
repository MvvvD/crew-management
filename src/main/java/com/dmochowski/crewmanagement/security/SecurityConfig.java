package com.dmochowski.crewmanagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;

import javax.sql.DataSource;

// test password bcrypt $2a$12$fjk1czJ5ErVlHiDG2WY8MefccPxVaRcq7HU1aLm9hctlWCm4S6K.2
@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager detailsManager = new JdbcUserDetailsManager(dataSource);
        return detailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity)throws Exception{
        httpSecurity.authorizeHttpRequests(configurer -> configurer
                .requestMatchers(HttpMethod.GET, "/employees/**").hasAnyRole("ADMIN","MANAGER","SUPERVISOR","WORKER")
                .requestMatchers(HttpMethod.POST, "/employees/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/employees/**").hasAnyRole("ADMIN","MANAGER")
                .requestMatchers(HttpMethod.DELETE, "/employees/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/tasks/**").hasAnyRole("ADMIN","MANAGER","SUPERVISOR","WORKER")
                .requestMatchers(HttpMethod.PUT, "/tasks/**").hasAnyRole("ADMIN","MANAGER, SUPERVISOR")
                .requestMatchers(HttpMethod.GET, "/employeesgdpr/**").permitAll());
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }
}
