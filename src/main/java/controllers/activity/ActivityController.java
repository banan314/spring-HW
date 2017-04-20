package controllers.activity;

import model.activity.Activity;
import model.activity.ActivityService;
import model.exception.NoSuchActivityException;
import model.user.User;
import model.user.UserService;
import model.exception.NoSuchUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Set;

/**
 * Created by Kamil on 9-Apr-17.
 */
@RestController
@RequestMapping(path = "activities")
public class ActivityController {

    private ActivityService activityService;

    public ActivityController(@Autowired ActivityService as) {
        this.activityService = as;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HttpStatus handleNotFoundResource() {
        return HttpStatus.valueOf(404);
    }

    @GetMapping(value = "/{id}")
    public Activity getActivity(@PathVariable(value = "id") int id) {
        try {
            return activityService.getById(id);
        } catch (NoSuchActivityException e) {
            throw new ResourceNotFoundException("Activity with id " + id + " not found!", null);
        }
    }

    @GetMapping(value = "")
    public Activity[] getAll() {
        Set<Activity> activitySet = activityService.getAll();
        return activitySet.toArray(new Activity[activitySet.size()]);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "")
    public void create(@Valid @RequestBody Activity activity) {
        activityService.addActivity(activity);
    }

    @PutMapping(value = "/{id}")
    public void update(@PathVariable(value = "id") int id, @RequestBody Activity activity) {
        //TODO: think of id
        activityService.updateActivity(id, activity);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteActivity(@PathVariable(value = "id") int id) {
        activityService.deleteActivity(id);
    }
}
