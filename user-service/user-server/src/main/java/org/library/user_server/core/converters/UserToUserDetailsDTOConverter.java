package org.library.user_server.core.converters;

import org.library.user_client.core.dto.UserDetailsDTO;
import org.library.user_server.dao.entity.User;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

public class UserToUserDetailsDTOConverter implements Converter<User, UserDetailsDTO> {
    @Override
    public UserDetailsDTO convert(User source) {
        return new UserDetailsDTO(source.getUuid(), source.getEmail(), source.getPassword(), List.of(source.getRole()));
    }
}
