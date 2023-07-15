package hw.spring.common.email;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest {
    EmailValidator emailValidator;

    @BeforeEach
    void setUp() {
        emailValidator = new EmailValidator();
    }

    @Test
    void givenValidEmail_thenIsValidReturnsTrue() {
        var email = "rietn@nikn.cuim";
        assertTrue(emailValidator.isValid(email, null));
    }

    @Test
    void givenInvalidEmail_thenIsValidReturnsFalse() {
        var email = "rietnnikn";
        assertFalse(emailValidator.isValid(email, null));
    }
}