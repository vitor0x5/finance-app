package io.github.vitor0x5.domains.user.controllers;

import io.github.vitor0x5.domains.user.dto.SignInDTO;
import io.github.vitor0x5.domains.user.services.authentication.UserAuthenticateService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController()
@RequestMapping("/")
public class SessionController {

    private final UserAuthenticateService userAuthenticateService;

    public SessionController(UserAuthenticateService userAuthenticateService) {
        this.userAuthenticateService = userAuthenticateService;
    }

    @PostMapping("sign-in")
    @ResponseStatus(HttpStatus.OK)
    public void authenticate(@RequestBody @Valid SignInDTO credentials, HttpServletResponse response) {
        Cookie cookie = userAuthenticateService.authenticate(credentials);
        response.addCookie(cookie);
    }

}
