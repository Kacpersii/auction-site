package edu.uph.ii.platformy.repositories;


import edu.uph.ii.platformy.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findRoleByType(Role.Types type);
}
