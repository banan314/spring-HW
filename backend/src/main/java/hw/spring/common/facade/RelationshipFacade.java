package hw.spring.common.facade;

import hw.spring.model.Activity;
import hw.spring.model.UserActivity;
import hw.spring.model.exception.NoSuchActivityException;
import hw.spring.model.user.User;
import hw.spring.services.activity.ActivityService;
import hw.spring.services.user.UserService;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Created by kamil on 12.05.17.
 */
@Component
public class RelationshipFacade {

    @Inject ActivityService activityService;
    @Inject UserService userService;

    public void assign(User user, Activity activity) {
        int userId = user.getId();
        int activityId = (int) activity.getId();

        UserActivity userActivity = new UserActivity();
        userActivity.setActivity(activity);
        userActivity.setTheUser(user);

        user.addActivity(activity.forUser(userActivity));
        userService.updateUser(userId, user);
        try {
            activityService.updateActivity(activityId, activity);
        } catch (NoSuchActivityException e) {
            //TODO: handle it
        }
    }

    public void assign(int userId, int activityId) {
        User user;
        user = userService.getById(userId).orElse(new User());
        Activity activity = activityService.getById(activityId).orElse(new Activity());
    }
}
