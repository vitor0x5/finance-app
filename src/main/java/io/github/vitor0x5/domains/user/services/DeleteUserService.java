package io.github.vitor0x5.domains.user.services;

import io.github.vitor0x5.domains.user.repositories.UsersRepository;
import io.github.vitor0x5.shared.errors.types.NotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class DeleteUserService {
    private final UsersRepository usersRepository;

    public DeleteUserService(UsersRepository repository) {
        this.usersRepository = repository;
    }

    @Transactional
    public void execute(UUID id) {
        usersRepository
                .findById(id)
                .map(user -> {
                    usersRepository.delete(user);
                    return user;
                }).orElseThrow(() ->
                new NotFoundException(NotFoundException.userNotFound));
    }
}
