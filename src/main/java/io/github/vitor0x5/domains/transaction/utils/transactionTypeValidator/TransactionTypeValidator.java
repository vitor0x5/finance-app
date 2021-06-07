package io.github.vitor0x5.domains.transaction.utils.transactionTypeValidator;

import io.github.vitor0x5.domains.transaction.TransactionTypes;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TransactionTypeValidator implements ConstraintValidator<TransactionTypeValidation, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.equals(TransactionTypes.INCOME.getType())
                || s.equals(TransactionTypes.OUTCOME.getType());
    }
}
