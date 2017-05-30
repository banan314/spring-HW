package hw.spring.services.user;

import hw.spring.model.user.User;
import hw.spring.model.exception.NoSuchUserException;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Kamil on 31-Mar-17.
 */
public interface UserService {
    Set<User> getAll();
    User getById(int id) throws NoSuchUserException;
    void addUser(User user);
    void deleteUser(int id);
    void updateUser(int id, User user);
}
