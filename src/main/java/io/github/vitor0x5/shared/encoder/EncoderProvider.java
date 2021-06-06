package io.github.vitor0x5.shared.encoder;

import io.github.vitor0x5.shared.encoder.implementations.BCrypt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EncoderProvider {
    @Bean
    public static Encoder encoder() {
        return new BCrypt();
    }
}
