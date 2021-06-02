package io.github.vitor0x5.domains.user.services.authentication;

import io.github.vitor0x5.domains.user.dto.TokenDTO;
import io.github.vitor0x5.domains.user.entities.AppUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    @Value("${security.jwt.duration}")
    private String duration;

    @Value("${security.jwt.secret}")
    private String key;

    public TokenDTO generateToken(String email) {
        long expString = Long.parseLong(duration);
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(expString);

        Instant instant = expirationTime.atZone(ZoneId.systemDefault()).toInstant();

        Date date = Date.from(instant);

        String token =  Jwts
                        .builder()
                        .setSubject(email)
                        .setExpiration(date)
                        .signWith(SignatureAlgorithm.HS512, key)
                        .compact();

        return new TokenDTO(email, token);
    }

    public boolean tokenIsValid (String token) {
        try{
            Claims claims = getClaims(token);
            Date expirationDateTime = claims.getExpiration();
            LocalDateTime expirationLocalDateTime = expirationDateTime
                    .toInstant()
                    .atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(expirationLocalDateTime);
        }catch(Exception ex) {
            return false;
        }
    }

    public String getLoggedUserEmail (String token) throws ExpiredJwtException{
        return getClaims(token).getSubject();
    }

    private Claims getClaims(String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }

}
