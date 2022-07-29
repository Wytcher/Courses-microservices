package br.com.apigateway.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl {

    @Value("${app-config.jwt.expiration}")
    private String expiration;

    @Value("${app-config.secrets.api-secret}")
    private String apiSecret;

    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(apiSecret).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e){
            throw new RuntimeException("Invalid Token");
        }
    }

    public Claims getTokenUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(apiSecret).build().parseClaimsJws(token).getBody();
    }

}
