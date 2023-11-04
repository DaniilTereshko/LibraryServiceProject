package org.library.user_service.service.impl;

import org.library.base_package.dto.user_service.UserRegistrationDTO;
import org.library.base_package.enums.user_service.UserRole;
import org.library.user_service.core.exception.EmailAlreadyTakenException;
import org.library.user_service.core.exception.NotFoundException;
import org.library.user_service.dao.entity.User;
import org.library.user_service.dao.repository.IUserRepository;
import org.library.user_service.service.api.IUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public class UserService implements IUserService {
    private static final String USER_NOT_FOUND= "Данный пользователь не найден";
    private static final String USER_ALREADY_EXISTS = "Пользователь уже зарегистрирован";
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserHolder userHolder;

    public UserService(IUserRepository userRepository, PasswordEncoder passwordEncoder, UserHolder userHolder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userHolder = userHolder;
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
    @Transactional(readOnly = true)
    @Override
    public User getContextUser() {
        UserDetails userDetails = this.userHolder.getUser();
        return this.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
    }
}
