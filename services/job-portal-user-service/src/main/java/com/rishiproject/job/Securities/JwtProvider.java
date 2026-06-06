package com.rishiproject.job.Securities;

import javax.crypto.SecretKey;

import com.rishiproject.job.Configurations.JwtProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class JwtProvider {
    private final JwtProperties jwtProperties;

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
    }

    public String generateToken(Authentication authentication, Long userId){
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String roles = populateAuthorities(authorities);

        return Jwts.builder()
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtProperties.getExpiration()))
                .claim("email", authentication.getName())
                .claim("authorities", roles)
                .claim("userId", userId)
                .signWith(getSecretKey())
                .compact();
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities){
        Set<String> auths = new HashSet<>();
        for(GrantedAuthority authority : authorities){
            auths.add(authority.getAuthority());
        }

        return String.join(",", auths);
    }
}
