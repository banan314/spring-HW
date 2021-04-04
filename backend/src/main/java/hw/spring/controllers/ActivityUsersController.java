package hw.spring.controllers;

import hw.spring.facades.RelationshipFacade;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * Created by kamil on 03.05.17.
 */
@RestController
public class ActivityUsersController {

    final RelationshipFacade relationshipFacade;

    @Inject
    public ActivityUsersController(RelationshipFacade relationshipFacade) {
        this.relationshipFacade = relationshipFacade;
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
