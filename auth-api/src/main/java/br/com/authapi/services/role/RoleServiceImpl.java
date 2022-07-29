package br.com.authapi.services.role;

import br.com.authapi.entities.role.Role;
import br.com.authapi.entities.role.RoleName;
import br.com.authapi.repository.role.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Set<Role> findRoleByName(RoleName roleName) {
        return roleRepository.findAllByRoleName(roleName);
    }
}
