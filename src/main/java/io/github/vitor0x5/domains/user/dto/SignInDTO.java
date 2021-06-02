package io.github.vitor0x5.domains.user.dto;

import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.domains.user.utils.passwordValidatorAnnotation.PasswordValidation;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class SignInDTO {
    @Email(message = "invalid email address")
    @NotNull(message = "email can't be null")
    public String email;

    @NotNull(message = "password can't be null")
    public String password;
}
