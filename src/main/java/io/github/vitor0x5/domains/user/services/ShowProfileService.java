package io.github.vitor0x5.domains.user.services;

import io.github.vitor0x5.domains.user.dto.UserResponseDataDTO;
import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.domains.user.repositories.UsersRepository;
import io.github.vitor0x5.shared.errors.types.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShowProfileService {
    private final UsersRepository usersRepository;
    private final ModelMapper mapper;

    public ShowProfileService(UsersRepository repository, ModelMapper mapper) {
        this.usersRepository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public UserResponseDataDTO execute(String userEmail) {
        AppUser user = usersRepository.findByEmail(userEmail)
                .orElseThrow(() -> new NotFoundException(NotFoundException.userNotFound));

        return mapper.map(user, UserResponseDataDTO.class);
    }
}
