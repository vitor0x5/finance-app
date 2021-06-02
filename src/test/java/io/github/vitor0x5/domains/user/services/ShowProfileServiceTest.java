package io.github.vitor0x5.domains.user.services;

import io.github.vitor0x5.domains.user.dto.SignUpDTO;
import io.github.vitor0x5.domains.user.dto.UserResponseDataDTO;
import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.domains.user.repositories.fakes.FakeUsersRepository;
import io.github.vitor0x5.domains.user.utils.mocks.UserMocksFactory;
import io.github.vitor0x5.shared.encoder.fakes.FakeEncoder;
import io.github.vitor0x5.shared.errors.types.NotFoundException;
import io.github.vitor0x5.shared.mappers.MapperProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class ShowProfileServiceTest {

    private ShowProfileService showProfileService;
    private FakeEncoder encoder;
    private ModelMapper mapper;
    private SignUpDTO user1;

    @BeforeEach
    public void before() {
        FakeUsersRepository repository = new FakeUsersRepository();
        FakeEncoder encoder = new FakeEncoder();
        MapperProducer mapperProducer = new MapperProducer();
        mapper = mapperProducer.get();

        showProfileService = new ShowProfileService(repository, mapper);

        user1 = UserMocksFactory.mockUser1SignUpDTO();
        repository.save(mapper.map(user1, AppUser.class));
    }

    @Test
    void testShowProfileWithValidEmail() {
        UserResponseDataDTO profileData = showProfileService.execute(user1.email);
        UserResponseDataDTO expectedProfileData = mapper.map(user1, UserResponseDataDTO.class);
        assertThatUsersAreEqual(expectedProfileData, profileData);
    }

    @Test
    void testShowProfileWithInvalidEmail() {
        try {
            showProfileService.execute("user2@email.com");
        } catch (RuntimeException e) {
            assertThat(e).isInstanceOf(NotFoundException.class);
        }
    }

    private void assertThatUsersAreEqual(UserResponseDataDTO user1, UserResponseDataDTO user2) {
        assert(user1.name).equals(user2.name);
        assert(user1.email).equals(user2.email);
    }
}