package hw.spring.model.repositories;

import hw.spring.model.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    boolean existsUserByEmail(String email);

    List<User> findAllByOrderById();
}
