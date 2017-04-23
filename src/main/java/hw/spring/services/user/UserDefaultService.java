package hw.spring.services.user;

import hw.spring.model.user.Sex;
import hw.spring.repositories.UserRepository;
import hw.spring.model.exception.NoSuchUserException;
import hw.spring.model.user.User;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Kamil on 31-Mar-17.
 */
@Service
public class UserDefaultService implements UserService {

    UserRepository userRepository;
    private Set<User> users = new HashSet<User>();

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
                "Joe334",
                (short) 20,
                Sex.FEMALE,
                new Date(1994, 3, 20)
        );
        mockUsers.add(mockUser);

        mockUser = new User(
                "Bloody_Mary",
                (short) 25,
                Sex.MALE,
                new Date(1989, 12, 21)
        );
        mockUsers.add(mockUser);
    }

    public Set<User> getAll() {
//        return users;
        Iterable<User> users = userRepository.findAll();
        Set<User> userSet = new HashSet<>();
        for (User user : users) {
            userSet.add(user);
        }
        return userSet;
    }

    public User getById(int id) throws NoSuchUserException {
        return userRepository.findOne(id);
        /*for(User user : users) {
            if(user.getId() == id) {
                return user;
            }
        }
        throw new NoSuchUserException();*/
    }

    public void addUser(User user) {
        /*if(hasUserId(user))
            user.setId((int) Math.floor(Math.random()*500000000));*/
        if (null != userRepository) {
            userRepository.save((User) null);
        }
//        users.add(user);
    }

    private boolean hasUserId(User user) {
        return user.getId() == 0;
    }

    public void deleteUser(int id) {
        users.removeIf(user -> user.getId() == id);
    }

    public void deleteAll() {
        users = null;
    }

    public void updateUser(int id, User user) {
        User found;
        try {
            found = getById(user.getId());
        } catch (NoSuchUserException e) {
            addUser(user); //should be?
            return;
        }
        users.remove((Object) found);
        users.add(user);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("");
        sb.append("Users = [");
        for (User user : users) {
            sb.append(user.toString()).append(", \n");
        }
        sb.append(']');
        return sb.toString();
    }
}
