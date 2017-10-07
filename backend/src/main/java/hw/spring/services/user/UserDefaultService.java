package hw.spring.services.user;

import hw.spring.model.user.JavadevUserDetails;
import hw.spring.model.user.Sex;
import hw.spring.repositories.UserRepository;
import hw.spring.model.exception.NoSuchUserException;
import hw.spring.model.user.User;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.*;

/**
 * Created by Kamil on 31-Mar-17.
 */
@Service
public class UserDefaultService implements UserService {

    UserRepository userRepository;

    Set<User> mockUsers = new HashSet<User>();

    @Inject
    UserDefaultService(UserRepository ur) {
        init();
        this.userRepository = ur;
        userRepository.save(mockUsers);
    }

    private void init() {
        if (mockUsers.isEmpty())
            createMockUsers();
    }

    private void createMockUsers() {
        User mockUser;

        mockUser = new User(
                "Joe334", "joe334@wp.pl", (short) 20, Sex.FEMALE, new Date(1994, 3, 20),
                "Joe Staunton");
        mockUsers.add(mockUser);

        mockUser = new User(
                "Bloody_Mary", "bmary@yandex.ru", (short) 25, Sex.MALE, new Date(1989, 12, 21),
                "Mary Dobrowolski");
        mockUsers.add(mockUser);
    }

    public Set<User> getAll() {
        Set<User> users = new HashSet<>();
        userRepository.findAll().forEach(user -> users.add(user));
        return users;
    }

    public User getById(int id) throws NoSuchUserException {
        User user = userRepository.findOne(id);
        if (null == user) {
            throw new NoSuchUserException();
        }
        return user;
    }

    public void addUser(User user) {
        if (null != userRepository) {
            userRepository.save(user);
        }
    }

    private boolean hasUserId(User user) {
        return user.getId() == 0;
    }

    public void deleteUser(int id) {
        userRepository.delete(id);
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

    public void updateUser(int id, User user) {
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String name) {
        if(name == "user") {
            Collection<? extends GrantedAuthority> authorities = Collections.singleton(() -> "STUDENT");
            return new JavadevUserDetails("user", "default", authorities);
        }
        //TODO: add to repository and return valid
        return null;
    }
}
