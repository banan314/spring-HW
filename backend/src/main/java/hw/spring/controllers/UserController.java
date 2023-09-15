package hw.spring.controllers;

import hw.spring.model.dto.UserDTO;
import hw.spring.model.dto.UserDTOConverter;
import hw.spring.model.user.User;
import hw.spring.services.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Kamil on 31-Mar-17.
 */
@RestController
@RequestMapping(path = "users")
public class UserController {

    private final UserService userService;

    @Inject
    public UserController(UserService us) {
        userService = us;
    }

    @ExceptionHandler(NoSuchElementException.class)
    public HttpStatus handleNotFoundResource() {
        return HttpStatus.NOT_FOUND;
    }

    @GetMapping(value = "")
    public List<UserDTO> getAllUsers() {
        return userService.getAll()
                .stream()
                .map(UserDTOConverter::fromUser)
                .toList();
    }

    @GetMapping(value = "/{id}")
    public User getUser(@PathVariable(value = "id") int id) {
        return userService.getById(id).get();
    }

    @PostMapping(value = "/new")
    public void post(@RequestBody User user) {
        userService.addUser(user);
    }

    @PutMapping(value = "/{id}")
    public void put(@RequestBody User user, @PathVariable int id) {
        user.setId(id);
        userService.updateUser(id, user);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }
}
