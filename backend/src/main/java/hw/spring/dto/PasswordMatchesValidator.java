package hw.spring.dto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
   public void initialize(PasswordMatches constraint) {
   }

   public boolean isValid(Object userDTO, ConstraintValidatorContext context) {
      UserDTO user = (UserDTO) userDTO;
      return user.getPassword().equals(user.getMatchingPassword());
   }
}
