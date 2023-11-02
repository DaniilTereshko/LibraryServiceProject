package org.library.user_server.config;

import jakarta.validation.Validator;
import org.library.user_server.core.converters.ConversionServiceFactory;
import org.library.user_server.dao.repository.IUserRepository;
import org.library.user_server.endpoints.web.util.JwtHandler;
import org.library.user_server.service.api.IAuthenticationService;
import org.library.user_server.service.api.IUserService;
import org.library.user_server.service.impl.AuthenticationService;
import org.library.user_server.service.impl.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserServiceConfig {
    @Bean
    public ConversionServiceFactory conversionService(){
        return new ConversionServiceFactory();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public IUserService userService(IUserRepository userRepository, PasswordEncoder passwordEncoder){
        return new UserService(userRepository,  passwordEncoder);
    }
    @Bean
    public IAuthenticationService authenticationService(IUserService userService, PasswordEncoder passwordEncoder, JwtHandler jwtHandler, Validator validator){
        return new AuthenticationService(userService, passwordEncoder, jwtHandler,validator);
    }
}
