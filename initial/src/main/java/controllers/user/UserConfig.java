package controllers.user;

import model.Sex;
import model.User;
import model.UserDefaultService;
import model.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Kamil on 31-Mar-17.
 */
@Configuration
public class UserConfig {
    @Bean
    public UserService userDefaultService() {
        UserService userService = (UserService) new UserDefaultService();

        Set<User> mockUsers;
        mockUsers = new HashSet<User>();

        User mockUser;

        mockUser = new User(
                1,
                "Joe334",
                (short) 20,
                Sex.FEMALE,
                new Date(1994, 3, 20)
        );
        mockUsers.add(mockUser);

        mockUser = new User(
                2,
                "Bloody_Mary",
                (short) 25,
                Sex.MALE,
                new Date(1989, 12, 21)
        );
        mockUsers.add(mockUser);

        for(User user : mockUsers) {
            userService.addUser(user);
        }

        return userService;
    }
}
