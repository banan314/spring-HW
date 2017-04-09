package controllers.activity;

import model.user.Activity;
import model.user.UserService;
import model.exception.NoSuchUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Kamil on 9-Apr-17.
 */
@RestController
@RequestMapping(path = "activity")
public class ActivityController {

    private UserService userService;

    public UserController(@Autowired UserService us) {
        this.userService = us;
    }

    @RequestMapping(value = "/user/id", method = RequestMethod.GET)
    public List<Activity> getActivitiesByUserId() {
        User specific;
        try {
            specific = userService.getById(id);
        } catch (NoSuchUserException e) {
            specific = null;
        }
        if(specific == null)
            return "No such user!";
        return specific.getActivities();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void post(@RequestBody User user) {
        
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public void Put(@RequestBody User user, @RequestParam int id) {

    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public void deleteUser(@RequestParam int id) {
        
    }
}
