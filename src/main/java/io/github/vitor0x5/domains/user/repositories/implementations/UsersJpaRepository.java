package io.github.vitor0x5.domains.user.repositories.implementations;

import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.domains.user.repositories.UsersRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsersJpaRepository extends JpaRepository<AppUser, UUID>, UsersRepository {
    Optional<AppUser> findByEmail(String email);
}
