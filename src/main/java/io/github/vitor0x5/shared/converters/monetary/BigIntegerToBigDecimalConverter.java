package io.github.vitor0x5.shared.converters.monetary;

import org.modelmapper.AbstractConverter;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigIntegerToBigDecimalConverter extends AbstractConverter<BigInteger, BigDecimal> {
    @Override
    protected BigDecimal convert(BigInteger bigInteger) {
        return BigDecimal.valueOf(bigInteger.longValue()).movePointLeft(2);
    }
}
