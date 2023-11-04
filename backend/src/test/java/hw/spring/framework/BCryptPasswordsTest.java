package hw.spring.framework;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BCryptPasswordsTest {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final String salt = "$2a$10$";

    @Test
    void getThisPassword() {
        final String password = "test";
        String hashedPassword = passwordEncoder.encode(password);

        assertEquals(salt, hashedPassword.substring(0, salt.length()));
    }

    @Test
    void testThisHash() {
        final String password = "admin";
        final String hashedPassword = "$2a$10$k00Ziyt.Y.IETiA.7QQJ1ODuRBdY5QXtCcxjNjkR7BLQCeCSkDxu6";

        assertTrue(passwordEncoder.matches(password, hashedPassword));
    }
}
