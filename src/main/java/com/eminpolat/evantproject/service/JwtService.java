package com.eminpolat.evantproject.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${security.jwt.secret}")
    private String SECRET_KEY;

    // ENV/Config'ten okunur; yoksa 60 dk default
    @Value("${security.jwt.exp.minutes:60}")
    private long jwtExpMinutes;

    public String findUsername(String token) {
        return exportToken(token, Claims::getSubject);
    }

    private <T> T exportToken(String token, Function<Claims, T> claimsTFunction) {
        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsTFunction.apply(claims);
    }

    private Key getKey() {
        byte[] key = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(key);
    }

    public boolean tokenControl(String jwt, UserDetails userDetails) {
        final String username = findUsername(jwt);
        return username.equals(userDetails.getUsername())
                && !exportToken(jwt, Claims::getExpiration).before(new Date());
    }

    public String generateToken(UserDetails user) {
        long expMs = Duration.ofMinutes(jwtExpMinutes).toMillis(); // ENV’den
        return Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expMs))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // (İsteğe bağlı) İhtiyaç olursa anlık farklı süreli token üretmek için:
    public String generateToken(UserDetails user, long minutes) {
        long expMs = Duration.ofMinutes(minutes).toMillis();
        return Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expMs))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}

