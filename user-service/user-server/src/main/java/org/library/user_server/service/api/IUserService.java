package org.library.user_server.service.api;

import org.library.user_client.core.dto.UserRegistrationDTO;
import org.library.user_server.dao.entity.User;

import java.util.Optional;

public interface IUserService {
    Optional<User> findByEmail(String email);
    User save(UserRegistrationDTO item);
}
