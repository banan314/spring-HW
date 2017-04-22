package model.user;

import controllers.data.UserRepository;
import model.exception.NoSuchUserException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Kamil on 31-Mar-17.
 */
public class UserDefaultService implements UserService {
    @Autowired
    private UserRepository userRepository;
    private Set<User> users = new HashSet<User>();

    public Set<User> getAll() {
//        return users;
        Iterable<User> users =userRepository.findAll();
        Set<User> userSet = new HashSet<>();
        for(User user : users) {
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
        if(null != userRepository) {
            userRepository.save((User)null);
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
        users.remove((Object)found);
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
