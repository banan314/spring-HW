package controllers.user;

import model.activity.Activity;
import model.exception.NoSuchUserException;
import model.user.User;
import model.user.UserService;
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
            throw new ResourceNotFoundException("User of id " + id + " not found!", new Resource() {
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
        return specific;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void post(@RequestBody User user) {
        userService.addUser(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void put(@RequestBody User user, @PathVariable int id) {
        user.setId(id);
        userService.updateUser(user);
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
