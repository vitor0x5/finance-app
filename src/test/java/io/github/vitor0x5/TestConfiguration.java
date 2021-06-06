package io.github.vitor0x5;

import io.github.vitor0x5.shared.encoder.Encoder;
import io.github.vitor0x5.shared.encoder.EncoderProvider;
import io.github.vitor0x5.shared.mappers.MapperProducer;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
public class TestConfiguration {

    @Bean
    public ModelMapper mapper() {
        MapperProducer mapperProducer = new MapperProducer();
        return mapperProducer.get();
    }

    @Bean
    public Encoder encoder() {
        return EncoderProvider.encoder();
    }
}
