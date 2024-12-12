//package com.staywell.config;
//
//import java.io.IOException;
//import java.util.Collection;
//import java.util.Date;
//import java.util.HashSet;
//import java.util.Set;
//
//import javax.crypto.SecretKey;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//@Configuration
//public class JwtTokenGeneratorFilter implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        // Logic trước khi controller xử lý request
//        return true;
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String path = request.getServletPath();
//        if (null == authentication) {
//            return;
//        }
//        if (path.equals("/staywell/admins/login")
//                || path.equals("/staywell/customers/login")
//                || path.equals("/staywell/hotels/login")) {
//
//            System.out.println("auth.getAuthorities "+authentication.getAuthorities());
//            SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes());
//            String jwt = Jwts.builder()
//                    .setIssuer("Ratan")
//                    .setSubject("JWT Token")
//                    .claim("username", authentication.getName())
//                    .claim("authorities", populateAuthorities(authentication.getAuthorities()))
//                    .setIssuedAt(new Date())
//                    .setExpiration(new Date(new Date().getTime()+ 30000000)) // expiration time of 8 hours
//                    .signWith(key).compact();
//            response.addHeader(SecurityConstants.JWT_HEADER, jwt);
//        }
//    }
//
//}
//
