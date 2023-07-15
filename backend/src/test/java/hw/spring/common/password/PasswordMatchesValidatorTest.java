package hw.spring.common.password;

import hw.spring.model.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PasswordMatchesValidatorTest {
    PasswordMatchesValidator passwordMatchesValidator;
    UserDTO userDTO;

    @BeforeEach
    void setUp() {
        passwordMatchesValidator = new PasswordMatchesValidator();
        userDTO = mock(UserDTO.class);
    }

    @Test
    void whenPasswordsMatch_thenIsValidReturnsTrue() {
        var password = "trniuf";
        when(userDTO.getPassword()).thenReturn(password);
        when(userDTO.getMatchingPassword()).thenReturn(password);

        assertTrue(passwordMatchesValidator.isValid(userDTO, null));
    }

    @Test
    void whenPasswordsDoNotMatch_thenIsValidReturnsFalse() {
        var password1 = "trniuf";
        var password2 = "friend";
        when(userDTO.getPassword()).thenReturn(password1);
        when(userDTO.getMatchingPassword()).thenReturn(password2);

        assertFalse(passwordMatchesValidator.isValid(userDTO, null));
    }
}