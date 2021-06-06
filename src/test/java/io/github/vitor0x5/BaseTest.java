package io.github.vitor0x5;

import io.github.vitor0x5.shared.encoder.Encoder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@ContextConfiguration(classes = TestConfiguration.class)
@TestPropertySource(properties = {
        "jdbc.driverClassName=org.h2.Driver",
        "jdbc.url=jdbc:h2:mem:myDb;DB_CLOSE_DELAY=-1",
        "spring.datasource.username=sa",
        "spring.datasource.password=sa",
        "hibernate.dialect=org.hibernate.dialect.H2Dialect",
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.flyway.enabled=false"
})
public class BaseTest {

    @Autowired
    public Encoder encoder;

    @Autowired
    public ModelMapper mapper;
}
