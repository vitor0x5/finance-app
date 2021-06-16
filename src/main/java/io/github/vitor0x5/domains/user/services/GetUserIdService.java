package io.github.vitor0x5.domains.user.services;

import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.domains.user.repositories.UsersRepository;
import io.github.vitor0x5.shared.exceptions.types.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class GetUserIdService {
    private final UsersRepository usersRepository;

    public GetUserIdService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Transactional
    public UUID execute(String userEmail) {
        AppUser user = usersRepository.findByEmail(userEmail)
                .orElseThrow(() -> new NotFoundException(NotFoundException.USER));

        return user.getId();
    }
}
