package org.library.user_service.core.converter;

import org.library.base_package.dto.user_service.UserDetailsDTO;
import org.library.user_service.dao.entity.User;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

public class UserToUserDetailsDTOConverter implements Converter<User, UserDetailsDTO> {
    @Override
    public UserDetailsDTO convert(User source) {
        return new UserDetailsDTO(source.getUuid(), source.getEmail(), source.getPassword(), List.of(source.getRole()));
    }
}
