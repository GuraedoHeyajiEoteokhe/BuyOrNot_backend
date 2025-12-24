package com.ambiguous.buyornot.common.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    @Value("${jwt.refresh-expiration}")
    private long jwtRefreshExpiration;

    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    // 로그인 access token 생성 (id)
    public String createAccessToken(String userId, String role, Long id) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration);
        return Jwts.builder()
                .subject(userId)
                .claim("role", role)
                .claim("id", id)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(secretKey)
                .compact();
    }

    // refresh token
    public String createRefreshToken(String userId) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtRefreshExpiration);
        return Jwts.builder()
                .subject(userId)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(secretKey)
                .compact();
    }

    public long getRefreshTokenExpiration() {
        return jwtRefreshExpiration;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            throw new BadCredentialsException("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            throw new BadCredentialsException("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            throw new BadCredentialsException("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException("JWT Token claims empty", e);
        }

    }

    public String getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }

    public Date getRefreshTokenExpiryDate(String refreshToken) {

        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(refreshToken)
                .getPayload();

        return claims.getExpiration(); // exp 값 추출
    }
}
