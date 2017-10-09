package hw.spring.services.user;

import hw.spring.model.exception.NoSuchUserException;
import hw.spring.model.user.Sex;
import hw.spring.model.user.User;
import hw.spring.repositories.UserRepository;
import lombok.val;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;

import java.util.logging.Logger;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.notNull;
import org.mockito.ArgumentMatchers;

import java.util.Arrays;
import java.util.Date;

public class UserDefaultServiceTest {
    private static final Logger LOG = Logger.getLogger(UserDefaultServiceTest.class.getName());

    private UserDefaultService service;
    private UserRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = mock(UserRepository.class);
        service = new UserDefaultService(repository);
    }

    @Test
    public void getAll() throws Exception {
        val mockUser = new User(
                "Joe334", "joe334@wp.pl", (short) 20, Sex.FEMALE, new Date(1994, 3, 20),
                "Joe Staunton");

        val mockUsers = Arrays.asList(mockUser);
        when(repository.findAll()).thenReturn(mockUsers);
        val users = service.getAll();
        assertEquals(mockUsers.size(), users.size());
    }

    @Test
    public void getById() throws Exception {
        try {
            service.getById(10);
        } catch (NoSuchUserException e) {
            LOG.info("no such user exception thrown");
        } finally {
            verify(repository).findOne(10);
        }
    }

    @Test
    public void addUser() throws Exception {
        val mockUser = new User(
                "Joe334", "joe334@wp.pl", (short) 20, Sex.FEMALE, new Date(1994, 3, 20),
                "Joe Staunton");

        service.addUser(mockUser);
        verify(repository).save(mockUser);
    }

    @Test
    public void deleteUser() throws Exception {
        service.deleteUser(10);
        verify(repository).delete(10);
    }

    @Ignore
    @Test
    public void deleteAll() throws Exception {
    }

    @Test
    public void updateUser() throws Exception {
        val mockUser = new User(
                "Joe334", "joe334@wp.pl", (short) 20, Sex.FEMALE, new Date(1994, 3, 20),
                "Joe Staunton");

        service.updateUser(10, mockUser);
        verify(repository).save(mockUser);
    }

}