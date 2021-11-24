package lt.codeacademy.projects.chc.coronahatersclub.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class ValidEmailValidator implements ConstraintValidator<ValidEmail,String> {
    @Override
    public void initialize(ValidEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext cvc) {
        String pattern = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$";
        return Pattern.matches(pattern,email);
    }
}
