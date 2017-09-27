package hw.spring.services.activity;

import hw.spring.model.exception.BadRequestException;
import hw.spring.repositories.ActivityRepository;
import hw.spring.model.exception.NoSuchActivityException;
import hw.spring.model.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by Kamil on 31-Mar-17.
 */
@Service
public class ActivityDefaultService implements ActivityService {

    private static final int MAX_ACTIVITIES = 8;
    List<Activity> mockActivities = new ArrayList<Activity>();

    private void init() {
        if (mockActivities.isEmpty())
            createMockActivities();
    }

    private void createMockActivities() {
        mockActivities.addAll(Arrays.asList(
                new Activity("lecturing", LocalDate.of(2016, 3, 20)),
                new Activity("exercising", LocalDate.of(2015, 12, 24)),
                new Activity("learning", LocalDate.of(2012, 9, 1)))
        );
        mockActivities.stream().forEach(activity -> activity.setLocation("Politechnika Rzeszowska"));
    }

    ActivityRepository activityRepository;

    @Inject
    ActivityDefaultService(ActivityRepository ar) {
        //init();
        activityRepository = ar;
        activityRepository.save(mockActivities);
    }

    public Set<Activity> getAll() {
        Set<Activity> activities = new HashSet<>();
        activityRepository.findAll().forEach(activity -> activities.add(activity));
        return activities;
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
        if (activityRepository.count() > MAX_ACTIVITIES) {
            throw new BadRequestException("Max activities count reached");
        }

        if (null != activityRepository) {
            activityRepository.save(activity);
        }
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

    public void updateActivity(long id, Activity activity) {
        activityRepository.save(activity);
    }
}
