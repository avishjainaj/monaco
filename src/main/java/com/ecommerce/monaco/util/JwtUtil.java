package com.ecommerce.monaco.util;

import ch.qos.logback.core.util.StringUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "tQUuHc2GDHFE9qcEiyCOIa6oSw9icRZmtQUuHc2GDHFE9qcEiyCOIa6oSw9icRZm";

    /**
     * Generate a token for the user
     * @param username
     * @return
     */
    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    /**
     * Extract the username from the token
     * @param token
     * @return
     */
    public String extractUsername(String token){
        return extractClaims(token).getSubject();
    }

    /**
     * Check if the token is valid
     * @param token
     * @param username
     * @return
     */
    public boolean isTokenValid(String token, String username){
        return username.equals(extractUsername(token)) && !isTokenExpired(token);
    }

    /**
     * Check if the token is expired
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    /**
     * Extract the claims from the token
     * @param token
     * @return
     */
    private Claims extractClaims(String token){
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

}
