package io.github.vitor0x5.shared.mappers;

import io.github.vitor0x5.shared.converters.monetary.BigDecimalToBigIntegerConverter;
import io.github.vitor0x5.shared.converters.monetary.BigIntegerToBigDecimalConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.context.annotation.Bean;

import java.util.function.Supplier;

@org.springframework.context.annotation.Configuration
public class MapperProducer implements Supplier<ModelMapper> {
    private ModelMapper mapper;

    public MapperProducer(){
        this.mapper = new ModelMapper();
        this.mapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PUBLIC);
        this.mapper.addConverter(new BigDecimalToBigIntegerConverter());
        this.mapper.addConverter(new BigIntegerToBigDecimalConverter());
    }

    @Override
    @Bean
    public ModelMapper get() {
        return mapper;
    }
}
