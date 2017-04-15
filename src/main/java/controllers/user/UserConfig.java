package controllers.user;

import model.activity.Activity;
import model.activity.ActivityDefaultService;
import model.activity.ActivityService;
import model.user.Sex;
import model.user.User;
import model.user.UserDefaultService;
import model.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.*;

/**
 * Created by Kamil on 31-Mar-17.
 */
@Configuration
public class UserConfig {
    Set<User> mockUsers = new HashSet<User>();
    List<Activity> mockActivities = new ArrayList<Activity>();

    @Bean
    public UserService userDefaultService() {
        init();

        UserService userService = (UserService) new UserDefaultService();
        for (User user : mockUsers) {
            userService.addUser(user);
        }
        return userService;
    }

    @Bean
    public ActivityService activityDefaultService() {
        init();

        ActivityService activityService = (ActivityService) new ActivityDefaultService();
        for (Activity activity : mockActivities) {
            activityService.addActivity(activity);
        }
        return  activityService;
    }

    private void init() {
        createMockUsers();
        createMockActivities();

        for(User user : mockUsers) {
            user.addActivity(mockActivities.get((int) Math.floor(Math.random()*3.0))
                .forUser(user)
            );
        }
    }

    private void createMockActivities() {
        mockActivities.addAll(Arrays.asList(
                new Activity(1, "lecturing", LocalDate.of(2016, 3, 20)),
                new Activity(2, "exercising", LocalDate.of(2015, 12, 24)),
                new Activity(3, "learning", LocalDate.of(2012, 9, 1)))
        );
    }

    private void createMockUsers() {
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
    }
}
