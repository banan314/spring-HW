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
            throw new ResourceNotFoundException("Activity with id " + id + " not found!", new Resource() {
                @Override
                public boolean exists() {
                    return false;
                }

                @Override
                public boolean isReadable() {
                    return false;
                }

                @Override
                public boolean isOpen() {
                    return false;
                }

                @Override
                public URL getURL() throws IOException {
                    return null;
                }

                @Override
                public URI getURI() throws IOException {
                    return null;
                }

                @Override
                public File getFile() throws IOException {
                    return null;
                }

                @Override
                public long contentLength() throws IOException {
                    return 0;
                }

                @Override
                public long lastModified() throws IOException {
                    return 0;
                }

                @Override
                public Resource createRelative(String relativePath) throws IOException {
                    return null;
                }

                @Override
                public String getFilename() {
                    return null;
                }

                @Override
                public String getDescription() {
                    return null;
                }

                @Override
                public InputStream getInputStream() throws IOException {
                    return null;
                }
            });
        }
    }

    @GetMapping(value = "")
    public Activity[] getAll() {
        Set<Activity> activitySet = activityService.getAll();
        return activitySet.toArray(new Activity[activitySet.size()]);
    }

    @PostMapping(value = "")
    public void create(@RequestBody Activity activity) {
        activityService.addActivity(activity);
    }

    @PutMapping(value = "/{id}")
    public void update(@PathVariable(value = "id") int id, @RequestBody Activity activity) {
        activityService.updateActivity(activity);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteActivity(@PathVariable(value = "id") int id) {
        activityService.deleteActivity(id);
    }
}
