package hw.spring.controllers;

import hw.spring.common.facade.RelationshipFacade;
import hw.spring.model.Activity;
import hw.spring.model.exception.NoSuchActivityException;
import hw.spring.model.exception.NoSuchUserException;
import hw.spring.model.user.User;
import hw.spring.services.activity.ActivityService;
import hw.spring.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "activities/{activityId}/users/{id}", method = RequestMethod.PUT)
    public void assignUser(@PathVariable(value = "id") int userId, @PathVariable(value = "activityId") int activityId) {
        relationshipFacade.assign(userId, activityId);
    }

    @RequestMapping(value = "users/{id}/activities/{activityId}", method = RequestMethod.PUT)
    public void assignActivity(@PathVariable(value = "id") int userId, @PathVariable(value = "activityId") int activityId) {
        relationshipFacade.assign(userId, activityId);
    }
}
