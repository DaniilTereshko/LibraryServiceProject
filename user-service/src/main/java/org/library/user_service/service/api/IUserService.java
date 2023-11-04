package org.library.user_service.service.api;

import org.library.base_package.dto.user_service.UserRegistrationDTO;
import org.library.user_service.dao.entity.User;

import java.util.Optional;

public interface IUserService {
    Optional<User> findByEmail(String email);
    User save(UserRegistrationDTO item);
    User getContextUser();
}
