package hw.spring.facades;

import hw.spring.model.activity.Activity;
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

    public void assign(int userId, int activityId) {
        User user = userService.getById(userId).orElse(new User());
        Activity activity = activityService.getById(activityId).orElse(new Activity());

        assign(user, activity);
    }

    private void assign(User user, Activity activity) {
        int userId = user.getId();
        int activityId = activity.getId();

        user.addActivity(activity)
                .forUser(user);

        userService.updateUser(userId, user);
        activityService.updateActivity(activityId, activity);
    }
}
