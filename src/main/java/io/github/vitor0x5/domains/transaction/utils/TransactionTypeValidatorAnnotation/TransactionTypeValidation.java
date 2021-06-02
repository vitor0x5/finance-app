package io.github.vitor0x5.domains.transaction.utils.TransactionTypeValidatorAnnotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = TransactionTypeValidator.class)
public @interface TransactionTypeValidation {
    String message() default "Transaction type must be: 'income' or 'outcome'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
