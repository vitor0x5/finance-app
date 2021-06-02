package io.github.vitor0x5.domains.user.dto;

public class TokenDTO {
    private String email;
    private String token;

    public TokenDTO(String email, String token) {
        this.email = email;
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }
}
