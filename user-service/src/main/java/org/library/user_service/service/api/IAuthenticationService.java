package org.library.user_service.service.api;

import org.library.base_package.dto.user_service.TokenDTO;
import org.library.base_package.dto.user_service.UserLoginDTO;
import org.library.base_package.dto.user_service.UserRegistrationDTO;

public interface IAuthenticationService {
    void registration(UserRegistrationDTO item);
    TokenDTO login(UserLoginDTO item);
}

