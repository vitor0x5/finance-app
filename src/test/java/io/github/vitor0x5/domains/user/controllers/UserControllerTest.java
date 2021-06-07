package io.github.vitor0x5.domains.user.controllers;

import io.github.vitor0x5.domains.configurations.BaseControllersTest;
import io.github.vitor0x5.domains.user.dto.SignUpDTO;
import io.github.vitor0x5.domains.user.dto.UserResponseDataDTO;
import io.github.vitor0x5.domains.user.services.CreateUserService;
import io.github.vitor0x5.domains.user.utils.mocks.UserMocksFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


class UserControllerTest extends BaseControllersTest {
    @MockBean
    public CreateUserService createUserService;

    @Test
    public void createUserWithValidData() throws Exception {
        var userDTO = UserMocksFactory.mockUser1SignUpDTO();
        mockCreateUserService(userDTO);

        mockMvc.perform(get("/sign-up")
                .content(objectMapper.writeValueAsString(userDTO))).
                andExpect(status().isOk());
    }

    public void mockCreateUserService(SignUpDTO userDTO) {
        var user1ResponseDataDTO = mapper.map(userDTO, UserResponseDataDTO.class);
        when(createUserService.execute(userDTO)).thenReturn(user1ResponseDataDTO);
    }



}