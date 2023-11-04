package org.library.library_service.service.api.user;

import org.library.base_package.dto.user_service.UserDetailsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "user-service", url = "${app.user-url}")
public interface IUserServiceClient {
     @GetMapping
     ResponseEntity<UserDetailsDTO> getContextUser(@RequestHeader("Authorization") String jwt);
}
