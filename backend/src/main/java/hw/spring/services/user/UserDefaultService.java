package hw.spring.services.user;

import hw.spring.model.exception.NoSuchUserException;
import hw.spring.model.user.JavadevUserDetails;
import hw.spring.model.user.User;
import hw.spring.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Kamil on 31-Mar-17.
 */
@Service
public class UserDefaultService implements UserService {

    @Inject UserRepository userRepository;

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return userRepository.findAllByOrderById();
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
        User loadedUser = userRepository.findByUsername(name);
        if (null == loadedUser) {
            return null;
        }
        return new JavadevUserDetails(loadedUser.getUsername(), loadedUser.getPassword(), authorities());
    }

    @Override
    public UserDetails loadUserByEmail(String email) {
        User loadedUser = userRepository.findByEmail(email);
        if (null == loadedUser) {
            return null;
        }
        return new JavadevUserDetails(loadedUser.getUsername(), loadedUser.getPassword(), authorities());
    }

    Collection<? extends GrantedAuthority> authorities() {
        return Collections.singleton(User.Role.STUDENT::toString);
    }


}
