package io.github.vitor0x5.domains.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.vitor0x5.domains.user.controllers.UserController;
import io.github.vitor0x5.shared.encoder.Encoder;
import io.github.vitor0x5.shared.encoder.EncoderProvider;
import io.github.vitor0x5.shared.mappers.MapperProducer;
import org.junit.jupiter.api.BeforeEach;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;

@WebMvcTest(UserController.class)
public class BaseControllersTest {
    @Autowired
    public ObjectMapper objectMapper;

    @Autowired
    public MockMvc mockMvc;

    public Encoder encoder;

    public ModelMapper mapper;

    public BaseControllersTest() {
        encoder = EncoderProvider.encoder();
        mapper = MapperProducer.getMapper();
    }
}
