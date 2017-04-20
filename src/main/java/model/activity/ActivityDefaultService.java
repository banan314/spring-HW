package model.activity;

import model.exception.NoSuchActivityException;
import model.activity.Activity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Kamil on 31-Mar-17.
 */
public class ActivityDefaultService implements ActivityService {
    private Set<Activity> activities = new HashSet<Activity>();

    public Set<Activity> getAll() {
        return activities;
    }

    public Activity getById(int id) throws NoSuchActivityException {
        for(Activity user : activities) {
            if(user.getId() == id) {
                return user;
            }
        }
        throw new NoSuchActivityException();
    }

    @Override
    public void addActivity(Activity activity) {
        if(hasntActivityId(activity))
            activity.setId((int) Math.floor(Math.random()*500000000));

        activities.add(activity);
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
        for (Activity user : activities) {
            sb.append(user.toString()).append(", \n");
        }
        sb.append(']');
        return sb.toString();
    }
}
