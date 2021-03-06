package hw.spring.services.user;

import hw.spring.dto.UserDTO;
import hw.spring.model.exception.EmailExistsException;
import hw.spring.model.user.User;

import java.util.List;
import java.util.Optional;

/**
 * Created by Kamil on 31-Mar-17.
 */
public interface UserService {
    List<User> getAll();
    Optional<User> getById(int id);
    void addUser(User user);
    void deleteUser(int id);
    void updateUser(int id, User user);
    User registerNewUserAccount(UserDTO accountDTO) throws EmailExistsException;
}
