package org.library.user_server.service.impl;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.library.user_client.core.dto.TokenDTO;
import org.library.user_client.core.dto.UserLoginDTO;
import org.library.user_client.core.dto.UserRegistrationDTO;
import org.library.user_server.core.exception.EmailAlreadyTakenException;
import org.library.user_server.core.exception.LoginException;
import org.library.user_server.dao.entity.User;
import org.library.user_server.endpoints.web.util.JwtHandler;
import org.library.user_server.service.api.IAuthenticationService;
import org.library.user_server.service.api.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public class AuthenticationService implements IAuthenticationService {
    private static final String USER_ALREADY_EXISTS = "Пользователь уже зарегистрирован";
    private static final String LOGIN_ERROR = "Неверные данные для входа в систему";
    private final IUserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtHandler jwtService;
    private final Validator validator;

    public AuthenticationService(IUserService userService, PasswordEncoder passwordEncoder, JwtHandler jwtService, Validator validator) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.validator = validator;
    }

    @Transactional
    @Override
    public void registration(UserRegistrationDTO item) {
        this.validate(item);
        this.userService.findByEmail(item.getMail()).ifPresent(u -> {throw new EmailAlreadyTakenException(USER_ALREADY_EXISTS, "mail");});
        this.userService.save(item);
    }
    @Transactional(readOnly = true)
    @Override
    public TokenDTO login(UserLoginDTO item) {
        User user = this.userService.findByEmail(item.getMail())
                .orElseThrow(() -> new LoginException(LOGIN_ERROR));
        if(!this.passwordEncoder.matches(item.getPassword(), user.getPassword())){
            throw new LoginException(LOGIN_ERROR);
        }
        String jwt = this.jwtService.generateAccessToken(user.getEmail());
        return new TokenDTO(jwt);
    }
    private <T> void validate(T item){
        Set<ConstraintViolation<T>> constraintViolations = this.validator.validate(item);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }
}
