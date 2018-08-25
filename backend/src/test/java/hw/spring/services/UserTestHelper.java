package hw.spring.services;

import hw.spring.model.user.Sex;
import hw.spring.model.user.User;

import java.sql.Date;

public class UserTestHelper {

    protected User fakeUser() {
        User user = new User();
        user.setUsername("Joe334");
        user.setName("Joe");
        user.setEmail("joe334@wp.pl");
        user.setId(20);
        user.setSex(Sex.FEMALE);
        user.setDateOfBirth(Date.valueOf("1994-3-20"));
        return user;
    }

    protected User fakeAnother() {
        User user = new User();
        user.setUsername("Bloody_Mary");
        user.setEmail("bmary@yandex.ru");
        user.setId(25);
        user.setSex(Sex.MALE);
        user.setDateOfBirth(Date.valueOf("1989-12-21"));
        user.setName("Mary");
        user.setSurname("Dobrowolski");
        return user;
    }
}
