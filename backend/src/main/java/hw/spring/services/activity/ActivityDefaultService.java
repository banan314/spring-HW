package hw.spring.services.activity;

import hw.spring.model.Activity;
import hw.spring.model.exception.BadRequestException;
import hw.spring.model.exception.NoSuchActivityException;
import hw.spring.repositories.ActivityRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Kamil on 31-Mar-17.
 */
@Service
public class ActivityDefaultService implements ActivityService {

    private static final int MAX_ACTIVITIES = 8;
    @Inject ActivityRepository activityRepository;

    public void setActivityRepository(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public List<Activity> getAll() {
        return activityRepository.findAllByOrderById();
    }

    public Activity getById(int id) throws NoSuchActivityException {
        Activity activity = activityRepository.findOne(id);
        if (null == activity) {
            throw new NoSuchActivityException();
        }
        return activity;
    }

    @Override
    public void addActivity(Activity activity) throws BadRequestException {
        assert null != activityRepository;

        if (activityRepository.count() > MAX_ACTIVITIES) {
            throw new BadRequestException("Max activities count reached");
        }

        activityRepository.save(activity);
    }

    private boolean hasntActivityId(Activity activity) {
        return activity.getId() == 0;
    }

    public void deleteActivity(int id) {
        activityRepository.delete(id);
    }

    public void deleteAll() {
        activityRepository.deleteAll();
    }

    public void updateActivity(long id, Activity activity) throws NoSuchActivityException {
        if (null == activityRepository.findOne((int) id)) {
            throw new NoSuchActivityException();
        }
        activityRepository.save(activity);
    }
}
