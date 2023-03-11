package com.spring.spring_security.Filter;

import com.spring.spring_security.constant.securityconstant;
import com.spring.spring_security.model.Authority;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.*;

public class JwtTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){
            SecretKey secretKey = Keys.hmacShaKeyFor(securityconstant.KEY.getBytes(StandardCharsets.UTF_8));
            String jwt= Jwts.builder().setSubject("JWT")
                    .claim("email",authentication.getName())
                    .claim("authorities",authorities(authentication.getAuthorities()))
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime()+30000))
                    .signWith(secretKey).compact();
            response.setHeader(securityconstant.HEADER,jwt);
        }
        filterChain.doFilter(request,response);
    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/engineer");
    }
    private String authorities(Collection<? extends GrantedAuthority> collections) {
        Set<String> auth = new HashSet<>();
        for (GrantedAuthority authority : collections) {
            auth.add(authority.getAuthority());
        }
        return String.join(",", auth);
    }

}