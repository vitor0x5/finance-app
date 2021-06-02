package io.github.vitor0x5.domains.user.utils.passwordValidatorAnnotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = PasswordValidator.class)
public @interface PasswordValidation {
    String message() default "The password must include letters numbers and symbols";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
