package com.cts.signup.security;

import org.springframework.stereotype.Component;

import com.cts.signup.bean.JwtUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGenerator {


    public String generate(JwtUser jwtuser) {


        Claims claims = Jwts.claims()
                .setSubject(jwtuser.getUserName());
        claims.put("userId", String.valueOf(jwtuser.getId()));
        claims.put("role", jwtuser.getRole());


        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "youtube")
                .compact();
    }
}
