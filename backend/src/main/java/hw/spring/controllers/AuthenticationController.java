package hw.spring.controllers;

import hw.spring.dto.UserDTO;
import hw.spring.model.exception.EmailExistsException;
import hw.spring.model.user.User;
import hw.spring.services.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class AuthenticationController {
    @Inject
    UserService userService;

//    @GetMapping(value = "/login")
//    public String login() {
//        return "login";
//    }

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
