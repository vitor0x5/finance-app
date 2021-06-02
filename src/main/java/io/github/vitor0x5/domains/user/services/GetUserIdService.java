package io.github.vitor0x5.domains.user.services;

import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.domains.user.repositories.UsersRepository;
import io.github.vitor0x5.shared.errors.types.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetUserIdService {
    private final UsersRepository usersRepository;

    public GetUserIdService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Transactional
    public UUID execute(String userEmail) {
        Optional<AppUser> user = usersRepository.findByEmail(userEmail);
        if(user.isPresent()){
            return user.get().getId();
        }
        throw new NotFoundException(NotFoundException.userNotFound);
    }
}
