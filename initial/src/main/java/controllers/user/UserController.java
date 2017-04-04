package controllers.user;

import model.User;
import model.UserService;
import model.exception.NoSuchUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Kamil on 31-Mar-17.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users/all", method = RequestMethod.GET)
    public String getAllUsers() {
        return userService.getAll().toString();
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
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

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public void post(@RequestBody User user) {
        userService.addUser(user);
    }

    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public void Put(@RequestBody User user, @RequestParam int id) {
        user.setId(id);
        userService.updateUser(user);
    }

    @RequestMapping(value = "/users", method = RequestMethod.DELETE)
    public void deleteUser(@RequestParam int id) {
        userService.deleteUser(id);
    }
}
