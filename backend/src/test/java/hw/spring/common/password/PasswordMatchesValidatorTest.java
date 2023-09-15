package hw.spring.common.password;

import hw.spring.model.dto.UserRegistrationDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PasswordMatchesValidatorTest {
    private PasswordMatchesValidator passwordMatchesValidator;
    private UserRegistrationDTO userRegistrationDTO;

    @BeforeEach
    void setUp() {
        passwordMatchesValidator = new PasswordMatchesValidator();
        userRegistrationDTO = mock(UserRegistrationDTO.class);
    }

    @Test
    void whenPasswordsMatch_thenIsValidReturnsTrue() {
        final var password = "trniuf";
        when(userRegistrationDTO.getPassword()).thenReturn(password);
        when(userRegistrationDTO.getMatchingPassword()).thenReturn(password);

        assertTrue(passwordMatchesValidator.isValid(userRegistrationDTO, null));
    }

    @Test
    void whenPasswordsDoNotMatch_thenIsValidReturnsFalse() {
        final var password1 = "trniuf";
        final var password2 = "friend";
        when(userRegistrationDTO.getPassword()).thenReturn(password1);
        when(userRegistrationDTO.getMatchingPassword()).thenReturn(password2);

        assertFalse(passwordMatchesValidator.isValid(userRegistrationDTO, null));
    }
}