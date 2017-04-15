package controllers.activity;

import model.activity.Activity;
import model.user.User;
import model.user.UserService;
import model.exception.NoSuchUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Kamil on 9-Apr-17.
 */
@RestController
@RequestMapping(path = "activities")
public class ActivityController {

    private UserService userService;

    public ActivityController(@Autowired UserService us) {
        this.userService = us;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HttpStatus handleNotFoundResource() {
        return HttpStatus.valueOf(404);
    }


}
