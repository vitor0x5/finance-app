package io.github.vitor0x5.domains.card.utils.type;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = CardTypeValidator.class)
public @interface CardTypeValidation {
    String message() default "Card type must be: 'c' or 'b' or 'o'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
