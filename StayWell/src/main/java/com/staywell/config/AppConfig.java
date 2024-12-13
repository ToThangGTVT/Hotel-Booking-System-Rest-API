package com.staywell.config;


import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class AppConfig implements WebMvcConfigurer {

    @Bean
    public SecurityFilterChain springSecurityConfiguration(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req -> {
                    req.requestMatchers(HttpMethod.POST, "/api/customers/register").permitAll();
                    req.requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/v3/api-docs*", "/swagger-resources/**", "/webjars/**").permitAll();
                    req.requestMatchers(HttpMethod.POST, "/api/admins/register").permitAll();
                    req.requestMatchers(HttpMethod.POST, "/api/hotels/register").permitAll();
                    req.requestMatchers(HttpMethod.POST, "/api/customers/login").permitAll();
                    req.requestMatchers(HttpMethod.POST, "/api/admins/login").permitAll();
                    req.requestMatchers(HttpMethod.POST, "/api/hotels/login").permitAll();
                    req.requestMatchers(HttpMethod.POST, "/api/rooms/add").hasRole("HOTEL");
                    req.requestMatchers(HttpMethod.PUT, "/api/customers/update").hasRole("CUSTOMER");
                    req.requestMatchers(HttpMethod.GET, "/api/customers/getToBeDeletedAccounts").hasRole("ADMIN");
                    req.anyRequest().authenticated();
                })
                .addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
