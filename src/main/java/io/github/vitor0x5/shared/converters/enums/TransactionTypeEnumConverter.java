package io.github.vitor0x5.shared.converters.enums;

import io.github.vitor0x5.domains.transaction.TransactionTypes;

import javax.persistence.AttributeConverter;

public class TransactionTypeEnumConverter  implements AttributeConverter<TransactionTypes, String> {
    @Override
    public String convertToDatabaseColumn(TransactionTypes transactionTypes) {
        return transactionTypes.getType();
    }

    @Override
    public TransactionTypes convertToEntityAttribute(String s) {
        return TransactionTypes.fromString(s);
    }
}
