package io.github.vitor0x5.domains.user.dto;

import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.domains.user.utils.passwordValidatorAnnotation.PasswordValidation;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SignUpDTO {
    @Email(message = "invalid email address")
    @NotNull(message = "email can't be null")
    public String email;

    @PasswordValidation
    @NotNull(message = "password can't be null")
    public String password;

    @NotEmpty(message = "Name can't be empty")
    @NotNull(message = "name can't be null")
    public String name;
}
