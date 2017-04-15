package controllers;

import model.activity.Activity;
import model.exception.NoSuchUserException;
import model.user.User;
import model.user.UserService;
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

    public UserActivitiesController(@Autowired UserService us) {
        this.userService = us;
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

    @RequestMapping(value = "/{id}/activity/{activityId}", method = RequestMethod.PUT)
    public void put(@RequestBody Activity activity, @PathVariable(value = "id") int userId, @PathVariable(value = "activitiId") int activityId) {

    }

    @RequestMapping(value = "/{id}/activities", method = RequestMethod.DELETE)
    public void deleteActivities(@PathVariable(value = "id") int userId) {

    }
}
