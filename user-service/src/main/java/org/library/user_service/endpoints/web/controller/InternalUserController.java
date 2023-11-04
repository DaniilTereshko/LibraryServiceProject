package org.library.user_service.endpoints.web.controller;

import org.library.base_package.dto.user_service.UserDetailsDTO;
import org.library.user_service.dao.entity.User;
import org.library.user_service.service.api.IUserService;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal/user")
public class InternalUserController {
    private final ConversionService conversionService;
    private final IUserService userService;

    public InternalUserController(ConversionService conversionService, IUserService userService) {
        this.conversionService = conversionService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserDetailsDTO> getContextUser() {
        User user = this.userService.getContextUser();
        UserDetailsDTO dto = this.conversionService.convert(user, UserDetailsDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
