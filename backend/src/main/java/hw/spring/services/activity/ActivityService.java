package hw.spring.services.activity;

import hw.spring.model.Activity;
import hw.spring.model.exception.NoSuchActivityException;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Kamil on 31-Mar-17.
 */
@Service
public interface ActivityService {
    Set<Activity> getAll();
    Activity getById(int id) throws NoSuchActivityException;
    void addActivity(Activity activity);
    void deleteActivity(int id);
    void updateActivity(long id, Activity user);
}
