package com.staywell.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class AppConfig {

    @Bean
    public SecurityFilterChain springSecurityConfiguration(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req -> {
                    req.requestMatchers(HttpMethod.POST, "/staywell/customers/register").permitAll();
                    req.requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/v3/api-docs*", "/swagger-resources/**", "/webjars/**").permitAll();
                    req.requestMatchers(HttpMethod.POST, "/staywell/admins/register").permitAll();
                    req.requestMatchers(HttpMethod.POST, "/staywell/hotels/register").permitAll();
                    req.requestMatchers(HttpMethod.POST, "/staywell/customers/login").permitAll();
                    req.requestMatchers(HttpMethod.POST, "/staywell/admins/login").permitAll();
                    req.requestMatchers(HttpMethod.POST, "/staywell/hotels/login").permitAll();
                    req.requestMatchers(HttpMethod.POST, "/staywell/rooms/add").hasRole("HOTEL");
                    req.requestMatchers(HttpMethod.PUT, "/staywell/customers/update").hasRole("CUSTOMER");
                    req.requestMatchers(HttpMethod.GET, "/staywell/customers/getToBeDeletedAccounts").permitAll();
                    req.anyRequest().authenticated();
                })
                .addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class);
        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
