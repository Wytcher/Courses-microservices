package br.com.authapi.services.role;

import br.com.authapi.entities.role.Role;
import br.com.authapi.entities.role.RoleName;

import java.util.Set;

public interface RoleService {

    Set<Role> findRoleByName(RoleName roleName);

}
