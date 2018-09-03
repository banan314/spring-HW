package hw.spring.controllers;

import hw.spring.common.NotImplemented;
import hw.spring.common.facade.RelationshipFacade;
import hw.spring.model.Activity;
import hw.spring.model.user.User;
import hw.spring.services.user.UserService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by kamil on 15.04.17.
 */
@RestController
@RequestMapping(path = "users")
public class UserActivitiesController {

    private final UserService userService;
    private final RelationshipFacade relationshipFacade;

    @Inject
    public UserActivitiesController(UserService userService, RelationshipFacade relationshipFacade) {
        this.userService = userService;
        this.relationshipFacade = relationshipFacade;
    }

    @GetMapping(value = "/{userId}/activities")
    public Collection<Activity> getActivitiesByUserId(@PathVariable(name = "userId") int userId) {
        User specific = fetchUserOrDefault(userId);
        if(specific == null)
            return null;
        return specific.getActivities();
    }

    private User fetchUserOrDefault(int id) {
        return userService.getById(id).orElse(null);
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
        Arrays.stream(activities).forEach(activity -> {
            relationshipFacade.assign(userId, activity.getId());
        });
    }

    @NotImplemented
    @RequestMapping(value = "/{id}/activities", method = RequestMethod.DELETE)
    public void deleteActivities(@PathVariable(value = "id") int userId) {

    }
}
