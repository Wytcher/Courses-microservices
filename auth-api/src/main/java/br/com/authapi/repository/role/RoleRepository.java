package br.com.authapi.repository.role;

import br.com.authapi.entities.role.Role;
import br.com.authapi.entities.role.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Set<Role> findAllByRoleName(RoleName name);

}
