package hw.spring.common.facade;

import hw.spring.model.Activity;
import hw.spring.model.UserActivity;
import hw.spring.model.exception.NoSuchActivityException;
import hw.spring.model.exception.NoSuchUserException;
import hw.spring.model.user.User;
import hw.spring.repositories.UserActivityRepository;
import hw.spring.services.activity.ActivityService;
import hw.spring.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Created by kamil on 12.05.17.
 */
@Component
public class RelationshipFacade {

    @Autowired
    private ActivityService activityService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserActivityRepository userActivityRepository;

    public RelationshipFacade() {
    }

    public void assign(User user, Activity activity) {
        int userId = user.getId();
        int activityId = (int) activity.getId();

        UserActivity userActivity = new UserActivity();
        userActivity.setActivity(activity);
        userActivity.setTheUser(user);

        user.addActivity(activity.forUser(userActivity));
        userService.updateUser(userId, user);
        activityService.updateActivity(activityId, activity);
    }

    public void assign(int userId, int activityId) {
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
    }
}