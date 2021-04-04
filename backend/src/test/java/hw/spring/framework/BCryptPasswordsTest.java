package hw.spring.framework;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.junit.Assert.*;

public class BCryptPasswordsTest {

    final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    final String salt = "$2a$10$";

    @Test
    public void getThisPassword() {
        String password = "test";
        String hashedPassword = passwordEncoder.encode(password);

        assertEquals(salt, hashedPassword.substring(0, salt.length()));
    }

    @Test
    public void testThisHash() {
        String password = "admin";
        String hashedPassword = "$2a$10$k00Ziyt.Y.IETiA.7QQJ1ODuRBdY5QXtCcxjNjkR7BLQCeCSkDxu6";

        assertTrue(passwordEncoder.matches(password, hashedPassword));
    }
}
