package io.github.vitor0x5.domains.user.services.authentication;

import io.github.vitor0x5.domains.user.dto.TokenDTO;
import io.jsonwebtoken.ExpiredJwtException;

public interface JwtService {
    TokenDTO generateToken(String email);

    boolean tokenIsValid(String token);

    String getLoggedUserEmail(String token) throws ExpiredJwtException;
}
