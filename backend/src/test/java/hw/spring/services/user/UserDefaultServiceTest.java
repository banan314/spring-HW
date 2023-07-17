package hw.spring.services.user;

import hw.spring.model.repositories.RoleRepository;
import hw.spring.model.user.User;
import hw.spring.model.repositories.UserRepository;
import hw.spring.helpers.UserTestHelper;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Ignore("install all first, then remove")
@RunWith(MockitoJUnitRunner.class)
public class UserDefaultServiceTest extends UserTestHelper {
    private static final Logger LOG = Logger.getLogger(UserDefaultServiceTest.class.getName());

    private UserDefaultService service;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    List<User> mockUsers = new ArrayList<>(2);

    @Before
    public void setUp() throws Exception {
        service = new UserDefaultService(userRepository, roleRepository, passwordEncoder);
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
        when(userRepository.findAllByOrderById()).thenReturn(mockUsers);
        List<User> users = service.getAll();
        assertEquals(mockUsers.size(), users.size());
    }

    @Test
    public void getById() throws Exception {
        try {
            service.getById(10).get();
        } catch (NoSuchElementException e) {
            LOG.info("no such user exceptions thrown");
        } finally {
            verify(userRepository).findById(10);
        }
    }

    @Test
    public void addUser() throws Exception {
        User mockUser = fakeAnother();

        service.addUser(mockUser);
        verify(userRepository).save(mockUser);
    }

    @Test
    public void deleteUser() throws Exception {
        when(userRepository.existsById(10)).thenReturn(true);
        service.deleteUser(10);
        verify(userRepository).deleteById(10);
    }

    @Ignore
    @Test
    public void deleteAll() throws Exception {
    }

    @Test
    public void updateUser() throws Exception {
        User mockUser = fakeAnother();

        service.updateUser(10, mockUser);
        verify(userRepository).save(mockUser);
    }

}