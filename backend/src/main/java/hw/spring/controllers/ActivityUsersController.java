package hw.spring.controllers;

import hw.spring.common.facade.RelationshipFacade;
import hw.spring.model.Activity;
import hw.spring.model.exception.NoSuchActivityException;
import hw.spring.model.exception.NoSuchUserException;
import hw.spring.model.user.User;
import hw.spring.services.activity.ActivityService;
import hw.spring.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

/**
 * Created by kamil on 03.05.17.
 */
@RestController
public class ActivityUsersController {

    @Autowired
    RelationshipFacade relationshipFacade;

    public ActivityUsersController() {
    }

    @PutMapping(value = "activities/{activityId}/users/{id}")
    public void assignUser(@PathVariable(value = "id") int userId, @PathVariable(value = "activityId") int activityId) {
        relationshipFacade.assign(userId, activityId);
    }

    @PutMapping(value = "users/{id}/activities/{activityId}")
    public void assignActivity(@PathVariable(value = "id") int userId, @PathVariable(value = "activityId") int activityId) {
        relationshipFacade.assign(userId, activityId);
    }
}
