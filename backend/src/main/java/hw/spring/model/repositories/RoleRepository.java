package hw.spring.model.repositories;

import hw.spring.model.user.role.Role;
import hw.spring.model.user.role.RoleName;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findRoleByName(RoleName roleName);
}
