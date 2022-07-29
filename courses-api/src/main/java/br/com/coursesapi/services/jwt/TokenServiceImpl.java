package br.com.coursesapi.services.jwt;

import br.com.coursesapi.exceptions.business.BusinessRuleException;
import br.com.coursesapi.models.User;
import br.com.coursesapi.models.UserMapper;
import br.com.coursesapi.models.UserPrincipal;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${app.config.secrets.api-secret}")
    private String apiSecret;

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

    public UserPrincipal buildUserPrincipal(String token) {
        var claims = Jwts.parserBuilder().setSigningKey(apiSecret).build().parseClaimsJws(token).getBody();

        var user = new User(claims);

        return UserMapper.userToPrincipal(user);
    }
}
