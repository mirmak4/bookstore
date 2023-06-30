/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.kazanik.basicfullstack.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import pl.kazanik.basicfullstack.security.exception.ApiAuthenticationException;

/**
 *
 * @author miron.maksymiuk
 */

@Component
public class JwtTokenUtils implements Serializable {

//    @Value("${secret}")
    private String jwtSecretKey = "secret";
    // 2 hours
    public static final long TOKEN_VALIDITY = 1_000 * 60 * 60 * 2;
    
    public String generateJwtToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder().setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512, jwtSecretKey)
                .compact();
    }
    
    public Boolean validateJwtToken(String token, UserDetails userDetails) {
        String userName = getUserNameFromToken(token);
        Claims claims = getClaimsFromToken(token);
        Boolean isTokenExpired = claims.getExpiration().before(new Date());
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired;
    }
    
    public String getUserNameFromToken(String token) {
        final Claims claims = getClaimsFromToken(token);
        return claims.getSubject();
    }
    
    public Claims getClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecretKey)
                .parseClaimsJws(token)
                .getBody();
    }
    
    public boolean validateTokenHeader(String token, String tokenHeader) {
        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
            return true;
        }
        return false;
    }
    
    public String extractUserNameFromToken(String tokenHeader) throws ApiAuthenticationException {
        String token = extractTokenFromTokenHeader(tokenHeader);
            
        try {
            String userName = getUserNameFromToken(token);
            return userName;
        }
        catch (IllegalArgumentException e) {
            throw new ApiAuthenticationException(
                    "illegal argument when filtering jwt request", e);
        }
        catch (ExpiredJwtException e) {
            throw new ApiAuthenticationException("jwt token expired", e);
        }
    }
    
    public String extractTokenFromTokenHeader(String tokenHeader) {
        String token = tokenHeader.substring(7);
        return token;
    }
    
}
