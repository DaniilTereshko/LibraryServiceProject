package org.library.user_server.service.api;

import org.library.user_client.core.dto.TokenDTO;
import org.library.user_client.core.dto.UserLoginDTO;
import org.library.user_client.core.dto.UserRegistrationDTO;

import java.util.UUID;

public interface IAuthenticationService {
    void registration(UserRegistrationDTO item);
    TokenDTO login(UserLoginDTO item);
}

