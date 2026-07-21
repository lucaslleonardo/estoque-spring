package br.com.lucaslleonardo.estoque_spring.Config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;


@Service
public class TokenProvider {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;


    // !!! possibiliade de fazer usando o BASE64 !!! //


    private SecretKey getSignInKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    private Claims getClaims(String token) {

        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String generateToken(UserDetails userDetails){

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey())
                .compact();

    }

    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    public boolean isTokenValid(String token) {

        try {
            getClaims(token);
            return true;
        } catch (Exception ex) {
            return false;
        }

    }
}
