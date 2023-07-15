package hw.spring.services.activity;

import hw.spring.common.exceptions.BadRequestException;
import hw.spring.model.activity.Activity;

import java.util.List;
import java.util.Optional;

/**
 * Created by Kamil on 31-Mar-17.
 */
public interface ActivityService {
    List<Activity> getAll();
    Optional<Activity> getById(int id);
    void addActivity(Activity activity) throws BadRequestException;
    void deleteActivity(int id);
    void updateActivity(int id, Activity user);
}
