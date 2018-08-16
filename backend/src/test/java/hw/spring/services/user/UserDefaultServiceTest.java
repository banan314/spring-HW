package hw.spring.services.user;

import hw.spring.model.exception.NoSuchUserException;
import hw.spring.model.user.Sex;
import hw.spring.model.user.User;
import hw.spring.repositories.UserRepository;
import lombok.val;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserDefaultServiceTest {
    private static final Logger LOG = Logger.getLogger(UserDefaultServiceTest.class.getName());

    private UserDefaultService service;

    @Mock
    private UserRepository repository;

    List<User> mockUsers = new ArrayList<>(2);

    @Before
    public void setUp() throws Exception {
        service = new UserDefaultService(repository);
        init();
    }

    private void init() {
        if (mockUsers.isEmpty()) {
            createMockUsers();
        }
    }

    private void createMockUsers() {
        User mockUser;

        mockUser = new User("Joe334", "joe334@wp.pl", (short) 20, Sex.FEMALE, Date.valueOf("1994-3-20"), "Joe Staunton");
        mockUsers.add(mockUser);

        mockUser = new User("Bloody_Mary", "bmary@yandex.ru", (short) 25, Sex.MALE, Date.valueOf("1989-12-21"), "Mary " +
                "Dobrowolski");
        mockUsers.add(mockUser);
    }

    @Test
    public void getAll() throws Exception {
        when(repository.findAllByOrderById()).thenReturn(mockUsers);
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
        val mockUser = new User("Joe334", "joe334@wp.pl", (short) 20, Sex.FEMALE, Date.valueOf("1994-3-20"), "Joe " +
                "Staunton");

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
        val mockUser = new User("Joe334", "joe334@wp.pl", (short) 20, Sex.FEMALE, Date.valueOf("1994-3-20"), "Joe " +
                "Staunton");

        service.updateUser(10, mockUser);
        verify(repository).save(mockUser);
    }

}