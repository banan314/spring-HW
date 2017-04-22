package model.activity;

import controllers.data.ActivityRepository;
import model.exception.NoSuchActivityException;
import model.activity.Activity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Kamil on 31-Mar-17.
 */
public class ActivityDefaultService implements ActivityService {
    private Set<Activity> activities = new HashSet<Activity>();

    @Autowired
    ActivityRepository activityRepository;

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
        Activity found;
        try {
            found = getById((int) activity.getId());
        } catch (NoSuchActivityException e) {
            addActivity(activity); //should be?
            return;
        }
        activities.remove((Object)found);
        activities.add(activity);
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
