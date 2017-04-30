package hw.spring.repositories;

import hw.spring.model.Activity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by kamil on 21.04.17.
 */
@org.springframework.stereotype.Repository
public interface ActivityRepository extends CrudRepository<Activity, Integer>{

}
