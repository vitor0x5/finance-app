package io.github.vitor0x5.domains.user.services;

import io.github.vitor0x5.domains.configurations.BaseServicesTest;
import io.github.vitor0x5.domains.user.dto.SignUpDTO;
import io.github.vitor0x5.domains.user.dto.UserResponseDataDTO;
import io.github.vitor0x5.domains.user.repositories.UsersRepository;

import io.github.vitor0x5.domains.user.utils.mocks.UserMocksFactory;
import io.github.vitor0x5.shared.exceptions.types.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateUserServiceTest extends BaseServicesTest {

    @Autowired
    private UsersRepository usersRepository;

    private CreateUserService createUserService;

    @BeforeEach
    public void before() {
        createUserService = new CreateUserService(usersRepository, encoder, mapper);
    }

    @Test
    public void testCreateUserWithValidEmailAndPassword() {
        SignUpDTO newUser = UserMocksFactory.mockUser1SignUpDTO();
        UserResponseDataDTO createdUserData = createUserService.execute(newUser);
        assertThatUsersAreEqual(newUser, createdUserData);
    }

    @Test
    public void testCreateUserWithAlreadyUsedEmail() {
        SignUpDTO newUser = UserMocksFactory.mockUser1SignUpDTO();
        UserResponseDataDTO createdUserData = createUserService.execute(newUser);

        newUser.name = "user2";
        try{
            createUserService.execute(newUser);
        } catch (RuntimeException ex) {
            assertThat(ex).isInstanceOf(BusinessException.class);
        }
    }

    @Test
    public void testCreateUserWithInvalidPassword() {
        SignUpDTO newUser = UserMocksFactory.mockUser2SignUpDTO();
        newUser.password = "abc";
        try{
            createUserService.execute(newUser);
        } catch (RuntimeException ex) {
            assertThat(ex).isInstanceOf(RuntimeException.class);
        }
    }

    @Test
    public void testCreateUserWithInvalidEmail() {
        SignUpDTO newUser = UserMocksFactory.mockUser2SignUpDTO();
        newUser.email = "user2email.com";
        try{
            createUserService.execute(newUser);
        } catch (RuntimeException ex) {
            assertThat(ex).isInstanceOf(BusinessException.class);
        }
    }

    public void assertThatUsersAreEqual(SignUpDTO user1, UserResponseDataDTO user2) {
        assert(user1.name).equals(user2.name);
        assert(user1.email).equals(user2.email);
    }
}