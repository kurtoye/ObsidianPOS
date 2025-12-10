package com.kurtoye.obsidianpos.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtConstant {

    public static final String JWT_HEADER = "Authorization";

    @Getter
    @Value("${jwt.secret}")
    public String jwtSecret;


}
