package br.com.coursesapi.models;

import br.com.coursesapi.models.role.Role;
import io.jsonwebtoken.Claims;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;
    private String email;
    private String password;
    private Boolean isEnabled;

    protected List<Role> roles;

    public User(Claims jwtClaims) {
        this.email = jwtClaims.get("email").toString();
        this.password = jwtClaims.get("password").toString();
        this.isEnabled = (Boolean) jwtClaims.get("isEnabled");
        this.roles = (List<Role>) jwtClaims.get("roles");
    }
}
