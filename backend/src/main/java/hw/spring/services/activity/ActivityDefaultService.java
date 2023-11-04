package hw.spring.services.activity;

import hw.spring.common.exceptions.BadRequestException;
import hw.spring.model.activity.Activity;
import hw.spring.model.repositories.ActivityRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class ActivityDefaultService implements ActivityService {

    private static final int MAX_ACTIVITIES = 8;
    @Inject
    ActivityRepository activityRepository;

    private static boolean hasntActivityId(Activity activity) {
        return activity.getId() == 0;
    }

    public void setActivityRepository(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public List<Activity> getAll() {
        return activityRepository.findAllByOrderById();
    }

    @Override
    public Optional<Activity> getById(int id) {
        return activityRepository.findById(id);
    }

    @Override
    public void addActivity(Activity activity) throws BadRequestException {
        assert null != activityRepository;

        if (activityRepository.count() > MAX_ACTIVITIES) throw new BadRequestException("Max activities count reached");

        activityRepository.save(activity);
    }

    @Override
    public void deleteActivity(int id) {
        if (activityRepository.existsById(id)) activityRepository.deleteById(id);
    }

    @Override
    public void updateActivity(int id, Activity activity) {
        if (null == activity) throw new NoSuchElementException();
        activityRepository.save(activity);
    }

    public void deleteAll() {
        activityRepository.deleteAll();
    }
}
