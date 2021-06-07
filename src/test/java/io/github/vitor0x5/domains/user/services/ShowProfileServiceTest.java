package io.github.vitor0x5.domains.user.services;

import io.github.vitor0x5.domains.configurations.BaseServicesTest;
import io.github.vitor0x5.domains.user.dto.UserResponseDataDTO;
import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.domains.user.repositories.UsersRepository;
import io.github.vitor0x5.domains.user.utils.mocks.UserMocksFactory;
import io.github.vitor0x5.shared.exceptions.types.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;

class ShowProfileServiceTest extends BaseServicesTest {
    AppUser user1;
    AppUser user2;

    @Autowired
    private UsersRepository usersRepository;

    private ShowProfileService showProfileService;

    @BeforeEach
    public void before() {
        showProfileService = new ShowProfileService(usersRepository, mapper);

        mockUsers();
    }

    @Test
    public void showProfileWithAValidEmail() {
        UserResponseDataDTO userProfile = showProfileService.execute(user1.getEmail());
        checkReturnedUserProfile(userProfile);
    }

    @Test
    public void showProfileWithAnInValidEmail() {
        try {
            showProfileService.execute("invalid@email.com");
        } catch (Exception e) {
            assertThat(e).isInstanceOf(NotFoundException.class);
        }
    }

    private void checkReturnedUserProfile(UserResponseDataDTO userProfile) {
        assert userProfile.email.equals(user1.getEmail());
        assert userProfile.name.equals(user1.getName());
    }

    private void mockUsers() {
        AppUser userToSave = UserMocksFactory.mockAppUser1();
        user1 = usersRepository.save(userToSave);

        userToSave = UserMocksFactory.mockAppUser2();
        user2 = usersRepository.save(userToSave);
    }

}