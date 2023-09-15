package hw.spring.common.password;

import hw.spring.model.dto.UserRegistrationDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
   @Override
   public void initialize(PasswordMatches constraint) {
   }

   @Override
   public boolean isValid(Object userDTO, ConstraintValidatorContext context) {
      UserRegistrationDTO user = (UserRegistrationDTO) userDTO;
      return user.getPassword().equals(user.getMatchingPassword());
   }
}
