package io.github.vitor0x5.shared.converters.enums;

import io.github.vitor0x5.domains.card.CardTypes;
import io.github.vitor0x5.domains.transaction.TransactionTypes;

import javax.persistence.AttributeConverter;

public class CardTypeEnumConverter implements AttributeConverter<CardTypes, String> {
    @Override
    public String convertToDatabaseColumn(CardTypes cardTypes) {
        return cardTypes.getType();
    }

    @Override
    public CardTypes convertToEntityAttribute(String s) {
        return CardTypes.fromString(s);
    }
}
