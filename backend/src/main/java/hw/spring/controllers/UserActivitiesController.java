package hw.spring.controllers;

import hw.spring.common.NotImplemented;
import hw.spring.model.Activity;
import hw.spring.model.exception.NoSuchActivityException;
import hw.spring.model.exception.NoSuchUserException;
import hw.spring.model.user.User;
import hw.spring.services.activity.ActivityService;
import hw.spring.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by kamil on 15.04.17.
 */
@RestController
@RequestMapping(path = "users")
public class UserActivitiesController {

    private UserService userService;
    private ActivityService activityService;

    public UserActivitiesController(@Autowired UserService us, @Autowired ActivityService as) {
        this.userService = us;
        this.activityService = as;
    }

    @RequestMapping(value = "/{userId}/activities", method = RequestMethod.GET)
    public List<Activity> getActivitiesByUserId(@PathVariable(name = "userId") int userId) {
        User specific = fetchUserOrDefault(userId);
        if(specific == null)
            return null;
        return specific.getActivities();
    }

    private User fetchUserOrDefault(int id) {
        User specific;
        try {
            specific = userService.getById(id);
        } catch (NoSuchUserException e) {
            specific = null;
        }
        return specific;
    }

    private Activity getActivityById(int userId, int activityId) {
        User user = fetchUserOrDefault(userId);
        for (Activity activity : user.getActivities()) {
            if(activity.getId() == activityId) {
                return activity;
            }
        }
        return null;
    }

    @PostMapping(value = "/{id}/activities")
    public void post(@PathVariable(value = "id") int userId, @RequestBody Activity activities[]) {
        User specific;
        try {
            specific = userService.getById((int) userId);
        } catch (NoSuchUserException e) {
            specific = null;
        }
        for(Activity activity : activities) {
            specific.addActivity(
                    activity.forUser(specific)
            );
        }
    }

    @RequestMapping(value = "/{id}/activities/{activityId}", method = RequestMethod.PUT)
    public void put(@PathVariable(value = "id") int userId, @PathVariable(value = "activityId") int activityId) {
        User user;
        try {
            user = userService.getById(userId);
        } catch (NoSuchUserException e) {
            return;
        }
        Activity activity;
        try {
            activity = activityService.getById(activityId);
        } catch (NoSuchActivityException e) {
            return;
        }
        user.addActivity(activity.forUser(user));
        userService.updateUser(userId, user);
        activityService.updateActivity(activityId, activity);
    }

    @NotImplemented
    @RequestMapping(value = "/{id}/activities", method = RequestMethod.DELETE)
    public void deleteActivities(@PathVariable(value = "id") int userId) {

    }
}
