package hw.spring.framework;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.junit.Assert.*;

public class BCryptPasswordsTest {

    @Test
    public void getThisPassword() {
        String password = "test";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);

        System.out.println(hashedPassword);
    }

    @Test
    public void testThisHash() {
        String password = "admin";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = "$2a$10$k00Ziyt.Y.IETiA.7QQJ1ODuRBdY5QXtCcxjNjkR7BLQCeCSkDxu6";

        assertTrue(passwordEncoder.matches(password, hashedPassword));
    }
}
