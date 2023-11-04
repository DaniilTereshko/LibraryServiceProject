package org.library.user_service.endpoints.web.controller;

import org.library.base_package.dto.user_service.TokenDTO;
import org.library.base_package.dto.user_service.UserLoginDTO;
import org.library.base_package.dto.user_service.UserRegistrationDTO;
import org.library.user_service.service.api.IAuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class AuthenticationController {
    private final IAuthenticationService authenticationService;

    public AuthenticationController(IAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody UserRegistrationDTO userRegistrationDTO){
        this.authenticationService.registration(userRegistrationDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody UserLoginDTO userLoginDTO){
        TokenDTO dto = this.authenticationService.login(userLoginDTO);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
