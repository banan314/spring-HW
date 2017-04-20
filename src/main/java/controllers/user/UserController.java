package controllers.user;

import common.NotImplemented;
import model.exception.NoSuchUserException;
import model.user.User;
import model.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

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

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HttpStatus handleNotFoundResource() {
        return HttpStatus.valueOf(404);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public User[] getAllUsers() {
        Set<User> allUsers = userService.getAll();
        return allUsers.toArray(new User[allUsers.size()]);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable(value = "id") int id) {
        User specific;
        try {
            specific = userService.getById(id);
        } catch (NoSuchUserException e) {
            throw new ResourceNotFoundException("User of id " + id + " not found!", null);
        }
        return specific;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void post(@RequestBody User user) {
        userService.addUser(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void put(@RequestBody User user, @PathVariable int id) {
        user.setId(id);
        userService.updateUser(id, user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }

    @DeleteMapping(value = "")
    @NotImplemented
    public void deleteAll() {
    }
}
