package io.github.vitor0x5.domains.user.repositories;

import io.github.vitor0x5.domains.user.entities.AppUser;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsersRepository {

    Optional<AppUser> findByEmail(String email);

    Optional<AppUser> findById(UUID id);

    AppUser save(AppUser user);

    void delete(AppUser user);
}
