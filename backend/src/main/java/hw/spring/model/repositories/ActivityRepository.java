package hw.spring.model.repositories;

import hw.spring.model.activity.Activity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by kamil on 21.04.17.
 */
@org.springframework.stereotype.Repository
public interface ActivityRepository extends CrudRepository<Activity, Integer>{
    List<Activity> findAllByOrderById();
}
