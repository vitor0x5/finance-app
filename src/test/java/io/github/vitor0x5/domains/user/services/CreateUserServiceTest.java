package io.github.vitor0x5.domains.user.services;

import io.github.vitor0x5.FinanceApplication;
import io.github.vitor0x5.domains.user.dto.SignUpDTO;
import io.github.vitor0x5.domains.user.dto.UserResponseDataDTO;
import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.domains.user.repositories.UsersRepository;

import io.github.vitor0x5.domains.user.utils.mocks.UserMocksFactory;
import io.github.vitor0x5.shared.errors.types.BusinessException;
import io.github.vitor0x5.shared.mappers.MapperProducer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(properties = {
        "spring.flyway.enabled=false"
})
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class CreateUserServiceTest {
    static SignUpDTO user1;

    @Autowired
    static UsersRepository usersRepository;

    @Autowired
    static CreateUserService createUserService;

    @Autowired
    static ModelMapper mapper;

    @BeforeAll
    static void setUp() {
        user1 = UserMocksFactory.mockUser1SignUpDTO();
        usersRepository.save(mapper.map(user1, AppUser.class));
    }

    @Test
    public void testCreateUserWithValidEmailAndPassword() {
        SignUpDTO newUser = UserMocksFactory.mockUser2SignUpDTO();
        UserResponseDataDTO createdUserData = createUserService.execute(newUser);
        UserResponseDataDTO newUserData = mapper.map(newUser, UserResponseDataDTO.class);
        assertThatUsersAreEqual(newUserData, createdUserData);
    }

    @Test
    public void testCreateUserWithAlreadyUsedEmail() {
        SignUpDTO newUser = UserMocksFactory.mockUser2SignUpDTO();
        newUser.email = user1.email;
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

    public void assertThatUsersAreEqual(UserResponseDataDTO user1, UserResponseDataDTO user2) {
        assert(user1.name).equals(user2.name);
        assert(user1.email).equals(user2.email);
    }
}