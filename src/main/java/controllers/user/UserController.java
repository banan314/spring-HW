package controllers.user;

import model.user.User;
import model.user.UserService;
import model.exception.NoSuchUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Kamil on 31-Mar-17.
 */
@RestController
@RequestMapping(path = "users")
public class UserController {

    private UserService userService;

    public UserController(@Autowired UserService us) {
        this.userService = us;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getAllUsers() {
        return userService.getAll().toString();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getUser(@RequestParam int id) {
        User specific;
        try {
            specific = userService.getById(id);
        } catch (NoSuchUserException e) {
            specific = null;
        }
        if(specific == null)
            return "No such user!";
        return specific.toString();
    }

    @GetMapping(path = "/{id}")
    public User unique(@PathVariable(name = "id") int id) {
        try {
            return userService.getById(id);
        } catch (NoSuchUserException e) {
            return null;
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void post(@RequestBody User user) {
        userService.addUser(user);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public void Put(@RequestBody User user, @RequestParam int id) {
        user.setId(id);
        userService.updateUser(user);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public void deleteUser(@RequestParam int id) {
        userService.deleteUser(id);
    }
}
