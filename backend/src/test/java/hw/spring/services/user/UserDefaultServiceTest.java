package hw.spring.services.user;

import hw.spring.model.user.Sex;
import hw.spring.model.user.User;
import hw.spring.repositories.UserRepository;
import hw.spring.services.UserTestHelper;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.inject.Inject;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserDefaultServiceTest extends UserTestHelper {
    private static final Logger LOG = Logger.getLogger(UserDefaultServiceTest.class.getName());

    @Inject
    private UserDefaultService service;

    @Mock
    private UserRepository repository;

    List<User> mockUsers = new ArrayList<>(2);

    @Before
    public void setUp() throws Exception {
        service.userRepository = repository;
        init();
    }

    private void init() {
        if (mockUsers.isEmpty()) {
            createMockUsers();
        }
    }

    private void createMockUsers() {
        User mockUser;

        mockUser = fakeUser();
        mockUsers.add(mockUser);

        mockUser = fakeAnother();
        mockUsers.add(mockUser);
    }

    @Test
    public void getAll() throws Exception {
        when(repository.findAllByOrderById()).thenReturn(mockUsers);
        List<User> users = service.getAll();
        assertEquals(mockUsers.size(), users.size());
    }

    @Test
    public void getById() throws Exception {
        try {
            service.getById(10).get();
        } catch (NoSuchElementException e) {
            LOG.info("no such user exception thrown");
        } finally {
            verify(repository).findById(10);
        }
    }

    @Test
    public void addUser() throws Exception {
        User mockUser = fakeAnother();

        service.addUser(mockUser);
        verify(repository).save(mockUser);
    }

    @Test
    public void deleteUser() throws Exception {
        service.deleteUser(10);
        verify(repository).deleteById(10);
    }

    @Ignore
    @Test
    public void deleteAll() throws Exception {
    }

    @Test
    public void updateUser() throws Exception {
        User mockUser = fakeAnother();

        service.updateUser(10, mockUser);
        verify(repository).save(mockUser);
    }

}