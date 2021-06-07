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

import java.util.UUID;

class DeleteUserServiceTest extends BaseServicesTest {
    UUID user1Id;

    @Autowired
    private UsersRepository usersRepository;

    private DeleteUserService deleteUserService;

    @BeforeEach
    public void before() {
        deleteUserService = new DeleteUserService(usersRepository);

        AppUser user = UserMocksFactory.mockAppUser1();
        usersRepository.save(user);
        user1Id = user.getId();
    }

    @Test
    public void deleteUserWithAnValidId() {
        deleteUserService.execute(user1Id);

        assert usersRepository.findById(user1Id).isEmpty();
    }

    @Test
    public void deleteUserWithAnInvalidId() {
        try {
            deleteUserService.execute(UUID.randomUUID());
        } catch (Exception e) {
            assertThat(e).isInstanceOf(NotFoundException.class);
        }
    }


}