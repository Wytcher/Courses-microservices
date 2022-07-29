package br.com.authapi.services.jwt;

import br.com.authapi.entities.user.User;
import br.com.authapi.entities.user.UserPrincipal;
import br.com.authapi.exceptions.business.BusinessRuleException;
import br.com.authapi.exceptions.business.ObjectNotFoundException;
import br.com.authapi.repository.user.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenServiceImpl {

    @Value("${app.config.jwt.expiration}")
    private String expiration;

    @Value("${app.config.secrets.api-secret}")
    private String apiSecret;

    @Autowired
    private UserRepository userRepository;

    public String generateToken(Authentication authentication) {
        var userPrincipal = (UserPrincipal) authentication.getPrincipal();

        var user = getUser(userPrincipal.getEmail());

        Date now = new Date();
        Date exp = new Date(now.getTime() + Long.parseLong(expiration));

        Claims claims = Jwts.claims().setSubject(userPrincipal.getUsername());
        claims.put("id", user.getId());
        claims.put("email", user.getEmail());
        claims.put("password", user.getPassword());
        claims.put("roles", user.getRoles());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer("AUTHAPI")
                .setIssuedAt(new Date())
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS512, apiSecret)
                .compact();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(apiSecret).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e){
            throw new BusinessRuleException("Invalid Token");
        }
    }

    public String getTokenUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(apiSecret).build().parseClaimsJws(token).getBody().getSubject();
    }

    private User getUser(String email){
        return userRepository.findUserByEmail(email).orElseThrow(() -> new ObjectNotFoundException("User not found"));
    }

}
