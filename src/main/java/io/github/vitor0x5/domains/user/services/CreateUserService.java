package io.github.vitor0x5.domains.user.services;

import io.github.vitor0x5.domains.user.dto.SignUpDTO;
import io.github.vitor0x5.domains.user.dto.UserResponseDataDTO;
import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.domains.user.repositories.UsersRepository;
import io.github.vitor0x5.shared.encoder.Encoder;
import io.github.vitor0x5.shared.errors.types.BusinessException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateUserService {

    private final UsersRepository usersRepository;
    private final Encoder encoder;
    private final ModelMapper mapper;

    public CreateUserService(UsersRepository repository, Encoder encoder, ModelMapper mapper) {
        this.usersRepository = repository;
        this.encoder = encoder;
        this.mapper = mapper;
    }

    @Transactional
    public UserResponseDataDTO execute(SignUpDTO userData) throws BusinessException{
        if(usersRepository.findByEmail(userData.email).isPresent()) {
            throw new BusinessException(
                    BusinessException.emailAlreadyUsed);
        }

        AppUser user = mapper.map(userData, AppUser.class);
        user.setPassword(encoder.encode(userData.password));

        usersRepository.save(user);
        return mapper.map(user, UserResponseDataDTO.class);
    }
}
