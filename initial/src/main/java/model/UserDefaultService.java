package model;

import model.exception.NoSuchUserException;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Kamil on 31-Mar-17.
 */
public class UserDefaultService implements UserService {
    private Set<User> users = new HashSet<User>();

    public Set<User> getAll() {
        return users;
    }

    public User getById(int id) throws NoSuchUserException {
        for(User user : users) {
            if(user.getId() == id) {
                return user;
            }
        }
        throw new NoSuchUserException();
    }

    public void addUser(User user) {
        if(hasUserId(user))
            user.setId((int) Math.floor(Math.random()*500000000));

        users.add(user);
    }
    private boolean hasUserId(User user) {
        return user.getId() == 0;
    }

    public void deleteUser(int id) {
        users.removeIf(user -> user.getId() == id);
    }

    public void updateUser(User user) {
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
