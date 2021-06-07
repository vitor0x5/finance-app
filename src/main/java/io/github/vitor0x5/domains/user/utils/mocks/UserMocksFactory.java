package io.github.vitor0x5.domains.user.utils.mocks;

import io.github.vitor0x5.domains.user.dto.SignUpDTO;
import io.github.vitor0x5.domains.user.entities.AppUser;

import java.util.UUID;

public class UserMocksFactory {

    public static AppUser mockAppUser1() {
        var user = new AppUser();
        user.setId(UUID.randomUUID());
        user.setName("User One");
        user.setPassword("abcd@1234");
        user.setEmail("user1@email.com");
        return user;
    }

    public static SignUpDTO mockUser1SignUpDTO() {
        SignUpDTO user = new SignUpDTO();
        user.email = "user1@email.com";
        user.password = "abcd@1234";
        user.name = "User One";
        return user;
    }

    public static AppUser mockAppUser2() {
        var user = new AppUser();
        user.setId(UUID.randomUUID());
        user.setName("User Two");
        user.setPassword("abcd@4321");
        user.setEmail("user2@email.com");
        return user;
    }

    public static SignUpDTO mockUser2SignUpDTO() {
        SignUpDTO user = new SignUpDTO();
        user.email = "user2@email.com";
        user.password = "abcd@4321";
        user.name= "User Two";
        return user;
    }
}
