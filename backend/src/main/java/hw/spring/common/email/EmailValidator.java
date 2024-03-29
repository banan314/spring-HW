package hw.spring.common.email;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
   private Pattern pattern;
   private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";

   public void initialize(ValidEmail constraint) {
   }

   public boolean isValid(String email, ConstraintValidatorContext context) {
      pattern = Pattern.compile(EMAIL_PATTERN);
      return pattern.matcher(email).matches();
   }
}
