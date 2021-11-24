package lt.codeacademy.projects.chc.coronahatersclub.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidEmailValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)


public @interface ValidEmail {
    String message() default "{invalidEmail}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
