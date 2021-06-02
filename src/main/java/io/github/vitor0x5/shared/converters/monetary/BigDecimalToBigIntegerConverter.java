package io.github.vitor0x5.shared.converters.monetary;

import org.modelmapper.AbstractConverter;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigDecimalToBigIntegerConverter extends AbstractConverter<BigDecimal, BigInteger> {
    @Override
    protected BigInteger convert(BigDecimal bigDecimal) {
        return BigInteger.valueOf(bigDecimal.movePointRight(2).longValue());
    }
}
