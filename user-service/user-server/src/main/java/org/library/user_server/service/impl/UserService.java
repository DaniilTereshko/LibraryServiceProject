package org.library.user_server.service.impl;

import org.library.user_client.core.dto.UserRegistrationDTO;
import org.library.user_client.core.enums.UserRole;
import org.library.user_server.core.exception.EmailAlreadyTakenException;
import org.library.user_server.dao.entity.User;
import org.library.user_server.dao.repository.IUserRepository;
import org.library.user_server.service.api.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public class UserService implements IUserService {
    private static final String USER_ALREADY_EXISTS = "Пользователь уже зарегистрирован";
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public User save(UserRegistrationDTO item) {
        this.userRepository.findByEmail(item.getMail())
                .ifPresent(u -> {throw new EmailAlreadyTakenException(USER_ALREADY_EXISTS, "mail");});
        User user = new User(UUID.randomUUID(), UserRole.USER, item.getMail(), passwordEncoder.encode(item.getPassword()));
        return this.userRepository.saveAndFlush(user);
    }
    @Transactional(readOnly = true)
    @Override
    public Optional<User> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }
}
