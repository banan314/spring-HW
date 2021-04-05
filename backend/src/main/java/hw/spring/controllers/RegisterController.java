package hw.spring.controllers;

import hw.spring.common.exceptions.EmailExistsException;
import hw.spring.model.dto.UserDTO;
import hw.spring.model.user.User;
import hw.spring.services.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(path = "${jwt.route.authentication.path}")
public class RegisterController {
    @Inject
    UserService userService;

    @PostMapping(value = "/register")
    public HttpStatus registerUser(@Valid @RequestBody UserDTO accountDTO) {
        Optional<User> registered = createUserAccount(accountDTO);
        if(registered.isPresent()) {
            return HttpStatus.CREATED;
        } else {
            return HttpStatus.CONFLICT;
        }
    }

    private Optional<User> createUserAccount(UserDTO accountDTO) {
        User registered;
        try {
            registered = userService.registerNewUserAccount(accountDTO);
        } catch (EmailExistsException e) {
            return Optional.empty();
        }
        return Optional.of(registered);
    }
}
