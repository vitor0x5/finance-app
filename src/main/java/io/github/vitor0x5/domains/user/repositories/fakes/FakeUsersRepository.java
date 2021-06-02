package io.github.vitor0x5.domains.user.repositories.fakes;

import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.domains.user.repositories.UsersRepository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class FakeUsersRepository implements UsersRepository {
    private ArrayList<AppUser> users;

    public FakeUsersRepository() {
        this.users = new ArrayList<AppUser>();
    }

    @Override
    public Optional<AppUser> findByEmail(String email) {
        Optional<AppUser> user = Optional.empty();
        for (AppUser u: users) {
            if(u.getEmail().equals(email))
                user = Optional.of(u);
        }
        return user;
    }

    @Override
    public Optional<AppUser> findById(UUID id) {
        Optional<AppUser> user = Optional.empty();
        for (AppUser u: users) {
            if(u.getId().equals(id))
                user = Optional.of(u);
        }
        return user;
    }

    @Override
    public AppUser save(AppUser user) {
        user.setId(UUID.randomUUID());
        users.add(user);
        return user;
    }

    @Override
    public void delete(AppUser user) {
        users.remove(user);
    }
}
