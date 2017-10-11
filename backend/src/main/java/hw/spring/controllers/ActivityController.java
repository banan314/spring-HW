package hw.spring.controllers;

import hw.spring.model.Activity;
import hw.spring.model.exception.BadRequestException;
import hw.spring.services.activity.ActivityService;
import hw.spring.model.exception.NoSuchActivityException;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

/**
 * Created by Kamil on 9-Apr-17.
 */
@RestController
@RequestMapping(path = "activities")
public class ActivityController {

    private ActivityService activityService;

    @Inject
    public ActivityController(ActivityService as) {
        this.activityService = as;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HttpStatus handleNotFoundResource() {
        return HttpStatus.valueOf(404);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpStatus handleBadRequest() {
        return HttpStatus.BAD_REQUEST;
    }

    @GetMapping(value = "/{id}")
    public Activity getActivity(@PathVariable(value = "id") int id) {
        try {
            return activityService.getById(id);
        } catch (NoSuchActivityException e) {
            throw createNotFoundException(id);
        }
    }

    private RuntimeException createNotFoundException(int id) {
        return new ResourceNotFoundException("Activity with id " + id + " not found!", null);
    }

    @GetMapping(value = "")
    public Activity[] getAll() {
        Collection<Activity> activities = activityService.getAll();
        Activity[] activitiesArray = activities.toArray(new Activity[0]);
        return activitiesArray;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "")
    public void create(@Valid @RequestBody Activity activity) throws BadRequestException {
        activityService.addActivity(activity);
    }

    @PutMapping(value = "/{id}")
    public HttpStatus update(@PathVariable(value = "id") int id, @RequestBody Activity activity) {
        try {
            activityService.updateActivity(id, activity);
            return HttpStatus.OK;
        } catch (NoSuchActivityException e) {
            return HttpStatus.NOT_FOUND;
        }
    }

    @DeleteMapping(value = "/{id}")
    public void deleteActivity(@PathVariable(value = "id") int id) {
        try {
            activityService.getById(id);
        } catch (NoSuchActivityException e) {
            throw createNotFoundException(id);
        }
        activityService.deleteActivity(id);
    }
}
