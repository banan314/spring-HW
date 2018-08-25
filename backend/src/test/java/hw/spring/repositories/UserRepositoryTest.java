package hw.spring.repositories;

import hw.spring.model.user.User;
import hw.spring.services.UserTestHelper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@DataJpaTest
class UserRepositoryTest extends UserTestHelper {

    @Autowired
    UserRepository userRepository;

    @Test
    public void existsUserByEmailWhenNewEmailShouldReturnFalse() throws Exception {
        User user = fakeUser();
        userRepository.save(user);
        assertEquals(false, userRepository.existsUserByEmail("bla@baram.com"));
    }

    @Test
    public void existsUserByEmailWhenTheSameEmailShouldReturnTrue() throws Exception {
        User user = fakeUser();
        userRepository.save(user);
        assertEquals(true, false);
        assertEquals(true, userRepository.existsUserByEmail(user.getEmail()));
    }
}