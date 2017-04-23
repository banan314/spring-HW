package hw.spring.services.activity;

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
    private Set<Activity> activities = new HashSet<Activity>();

    List<Activity> mockActivities = new ArrayList<Activity>();

    private void init() {
        if(mockActivities.isEmpty())
            createMockActivities();
    }
    private void createMockActivities() {
        mockActivities.addAll(Arrays.asList(
                new Activity("lecturing", LocalDate.of(2016, 3, 20)),
                new Activity("exercising", LocalDate.of(2015, 12, 24)),
                new Activity("learning", LocalDate.of(2012, 9, 1)))
        );
    }

    ActivityRepository activityRepository;

    @Inject
    ActivityDefaultService(ActivityRepository ar) {
        init();
        activityRepository = ar;
        activityRepository.save(mockActivities);
    }

    public Set<Activity> getAll() {
        Iterable<Activity> Activitys = activityRepository.findAll();
        Set<Activity> ActivitySet = new HashSet<>();
        for(Activity Activity : Activitys) {
            ActivitySet.add(Activity);
        }
        return ActivitySet;
//        return activities;
    }

    public Activity getById(int id) throws NoSuchActivityException {
        return activityRepository.findOne(id);
        /*for(Activity Activity : activities) {
            if(Activity.getId() == id) {
                return Activity;
            }
        }
        throw new NoSuchActivityException();*/
    }

    @Override
    public void addActivity(Activity activity) {
        if(hasntActivityId(activity))
            activity.setId((int) Math.floor(Math.random()*500000000));

        if(null != activityRepository) {
            activityRepository.save(activity);
        }
//        activities.add(activity);
    }

    private boolean hasntActivityId(Activity activity) {
        return activity.getId() == 0;
    }

    public void deleteActivity(int id) {
        activities.removeIf(activity -> activity.getId() == id);
    }

    public void deleteAll() {
        activities = null;
    }

    public void updateActivity(long id, Activity activity) {
        /*Activity found;
        try {
            found = getById((int) activity.getId());
        } catch (NoSuchActivityException e) {
            addActivity(activity); //should be?
            return;
        }
        activities.remove((Object)found);
        activities.add(activity);*/
        activityRepository.save(activity);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("");
        sb.append("Activitys = [");
        for (Activity Activity : activities) {
            sb.append(Activity.toString()).append(", \n");
        }
        sb.append(']');
        return sb.toString();
    }
}
