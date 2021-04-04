package hw.spring.model.repositories;

import hw.spring.model.user.role.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    @Query(value = "select * from roles where name = ?1", nativeQuery = true)
    Role findRole(String roleName);
}
