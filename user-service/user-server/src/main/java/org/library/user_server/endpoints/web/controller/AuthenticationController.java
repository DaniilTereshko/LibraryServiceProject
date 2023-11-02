package org.library.user_server.endpoints.web.controller;

import org.library.user_client.core.dto.TokenDTO;
import org.library.user_client.core.dto.UserLoginDTO;
import org.library.user_client.core.dto.UserRegistrationDTO;
import org.library.user_server.service.api.IAuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
    public ResponseEntity<String> login(@RequestBody UserLoginDTO userLoginDTO){
        TokenDTO dto = this.authenticationService.login(userLoginDTO);
        return new ResponseEntity<>(dto.getToken(), HttpStatus.OK);
    }
}
