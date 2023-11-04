package org.library.book_service.service.impl.user;

import org.library.base_package.dto.user_service.UserDetailsDTO;
import org.library.book_service.service.api.user.IUserService;
import org.library.book_service.service.api.user.IUserServiceClient;

public class UserService implements IUserService {
    private final IUserServiceClient userServiceClient;

    public UserService(IUserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }

    @Override
    public UserDetailsDTO getContextUser(String jwt) {
        return this.userServiceClient.getContextUser("Bearer " + jwt).getBody();
    }

}
