package hw.spring.repositories;

import hw.spring.model.UserActivity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kamil on 12.05.17.
 */
@Repository
public interface UserActivityRepository extends CrudRepository<UserActivity, Integer> {
}
