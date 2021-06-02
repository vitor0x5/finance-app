package io.github.vitor0x5.domains.transaction.utils.TransactionTypeValidatorAnnotation;

import io.github.vitor0x5.domains.transaction.TransactionTypes;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TransactionTypeValidator implements ConstraintValidator<TransactionTypeValidation, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.equals(TransactionTypes.INCOME.getType())
                || s.equals(TransactionTypes.OUTCOME.getType());
    }
}
