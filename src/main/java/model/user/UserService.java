package model.user;

import model.exception.NoSuchUserException;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Kamil on 31-Mar-17.
 */
@Service
public interface UserService {
    Set<User> getAll();
    User getById(int id) throws NoSuchUserException;
    void addUser(User user);
    void deleteUser(int id);
    void updateUser(User user);
}
