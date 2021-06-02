package io.github.vitor0x5.domains.user.utils.mocks;

import io.github.vitor0x5.domains.user.dto.SignUpDTO;

public class UserMocksFactory {
    public static SignUpDTO mockUser1SignUpDTO() {
        SignUpDTO user = new SignUpDTO();
        user.email = "user1@email.com";
        user.password = "abcd@1234";
        user.name = "User One";
        return user;
    }

    public static SignUpDTO mockUser2SignUpDTO() {
        SignUpDTO user = new SignUpDTO();
        user.email = "user2@email.com";
        user.password = "abcd@1234";
        user.name= "User Two";
        return user;
    }
}
