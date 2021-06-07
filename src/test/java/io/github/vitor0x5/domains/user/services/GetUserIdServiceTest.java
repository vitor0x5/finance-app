package io.github.vitor0x5.domains.user.services;

import io.github.vitor0x5.domains.configurations.BaseServicesTest;
import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.domains.user.repositories.UsersRepository;
import io.github.vitor0x5.domains.user.utils.mocks.UserMocksFactory;
import io.github.vitor0x5.shared.exceptions.types.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class GetUserIdServiceTest extends BaseServicesTest {

    AppUser user1;
    AppUser user2;

    @Autowired
    private UsersRepository usersRepository;

    private GetUserIdService getUserIdService;

    @BeforeEach
    public void before() {
        getUserIdService = new GetUserIdService(usersRepository);

        mockUsers();
    }

    @Test
    public void getUserIdWithAValidEmail() {
        assert getUserIdService.execute(user1.getEmail()).equals(user1.getId());
    }

    @Test
    public void getUserIdWithAnInValidEmail() {
        try {
            getUserIdService.execute("invalid@email.com");
        } catch (Exception e) {
            assertThat(e).isInstanceOf(NotFoundException.class);
        }
    }

    private void mockUsers() {
        AppUser userToSave = UserMocksFactory.mockAppUser1();
        user1 = usersRepository.save(userToSave);

        userToSave = UserMocksFactory.mockAppUser2();
        user2 = usersRepository.save(userToSave);
    }

}