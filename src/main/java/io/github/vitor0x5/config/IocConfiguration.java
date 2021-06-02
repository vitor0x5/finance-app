package io.github.vitor0x5.config;

import io.github.vitor0x5.shared.encoder.Encoder;
import io.github.vitor0x5.shared.encoder.implementations.BCrypt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IocConfiguration {
    @Bean
    public Encoder encoder() {
        return new BCrypt();
    }
}
