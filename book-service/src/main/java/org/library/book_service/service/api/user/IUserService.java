package org.library.book_service.service.api.user;

import org.library.base_package.dto.user_service.UserDetailsDTO;

public interface IUserService {
    UserDetailsDTO getContextUser(String jwt);

}
