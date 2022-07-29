package br.com.coursesapi.models;

import br.com.coursesapi.models.role.Role;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserPrincipal userToPrincipal(User user) {
        ObjectMapper mapper = new ObjectMapper();
        List<Role> roles = mapper.convertValue(user.getRoles(), new TypeReference<List<Role>>() {});

        UserPrincipal userMap = new UserPrincipal();
        List<SimpleGrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority("" + role.getRoleName())).collect(Collectors.toList());

        userMap.setEmail(user.getEmail());
        userMap.setPassword(user.getPassword());
        userMap.setEnabled(true);
        userMap.setAuthorities(authorities);
        return userMap;
    }
}
