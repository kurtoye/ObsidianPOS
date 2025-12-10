package com.kurtoye.obsidianpos.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;





@Service
public class JwtProvider {

    private final JwtConstant jwtConstant;
    private SecretKey secretKey;

    public JwtProvider(JwtConstant jwtConstant) {
        this.jwtConstant = jwtConstant;
    }

    @PostConstruct
    public void init() {
        this.secretKey = Keys.hmacShaKeyFor(
                jwtConstant.getJwtSecret().getBytes()
        );
    }

    public SecretKey getSigningKey() {
        return secretKey;
    }

    public String generateToken(Authentication authentication) {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        String roles = populateAuthorities(authorities);
        return Jwts.builder()
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + 86400000))
                .claim("email", authentication.getName())
                .claim("authorities", roles)
                .signWith(secretKey)
                .compact();
    }

    public String getEmailFromToken(String jwt) {

        jwt = jwt.substring(7);
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(jwt).getPayload();

        return String.valueOf(claims.get("email"));
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {

        Set<String> auths = new HashSet<String>();
        for (GrantedAuthority grantedAuthority : authorities) {
            auths.add(grantedAuthority.getAuthority());
        }
        return String.join(",", auths);
    }


}
