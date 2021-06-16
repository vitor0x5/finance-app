package io.github.vitor0x5.domains.card.utils.type;

import io.github.vitor0x5.domains.card.CardTypes;
import io.github.vitor0x5.domains.transaction.TransactionTypes;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CardTypeValidator implements ConstraintValidator<CardTypeValidation, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return CardTypes.toStringList().contains(s);
    }
}
