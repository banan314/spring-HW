package controllers.hello.model;

import model.user.Sex;
import model.user.User;
import model.user.UserDefaultService;
import org.junit.Before;
import org.junit.Test;

import org.junit.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Kamil on 31-Mar-17.
 */
public class UserDefaultServiceTest {
    UserDefaultService userService;

    @Before
    public void setUp() {
        userService = new UserDefaultService();
    }

    @Test
    public void testNotNull() throws Exception{
        assert userService != null;
    }

    @Test
    public void testConfig() {
        Set<User> mockUsers;
        mockUsers = new HashSet<User>();

        User mockUser;

        mockUser = new User(
                1,
                "Maria23",
                (short) 20,
                Sex.FEMALE,
                new Date(1994, 3, 20)
        );
        mockUsers.add(mockUser);

        mockUser = new User(
                2,
                "dynamite Joe",
                (short) 25,
                Sex.MALE,
                new Date(1989, 12, 21)
        );
        mockUsers.add(mockUser);

        for(User user : mockUsers) {
            userService.addUser(user);
        }

        Assert.assertNotNull(userService);
    }
}
