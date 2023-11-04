package org.library.user_service.config;

import jakarta.validation.Validator;
import org.library.user_service.dao.repository.IUserRepository;
import org.library.user_service.endpoints.web.util.JwtHandler;
import org.library.user_service.service.api.IAuthenticationService;
import org.library.user_service.service.api.IUserService;
import org.library.user_service.service.impl.AuthenticationService;
import org.library.user_service.service.impl.UserHolder;
import org.library.user_service.service.impl.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserServiceConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public IUserService userService(IUserRepository userRepository, PasswordEncoder passwordEncoder, UserHolder userHolder){
        return new UserService(userRepository,  passwordEncoder, userHolder);
    }
    @Bean
    public IAuthenticationService authenticationService(IUserService userService, PasswordEncoder passwordEncoder, JwtHandler jwtHandler, Validator validator){
        return new AuthenticationService(userService, passwordEncoder, jwtHandler,validator);
    }
}
