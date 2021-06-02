package io.github.vitor0x5.shared.converters.monetary;

import javax.persistence.AttributeConverter;
import java.math.BigDecimal;
import java.math.BigInteger;

public class MonetaryTypeAttributeConverter implements AttributeConverter<BigDecimal, BigInteger> {
    @Override
    public BigInteger convertToDatabaseColumn(BigDecimal attribute) {
        return BigInteger.valueOf(attribute.movePointRight(2).longValue());
    }

    @Override
    public BigDecimal convertToEntityAttribute(BigInteger dbData) {
        return BigDecimal.valueOf(dbData.longValue()).movePointLeft(2);
    }
}
