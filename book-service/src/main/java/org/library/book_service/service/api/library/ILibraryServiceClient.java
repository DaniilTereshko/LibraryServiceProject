package org.library.book_service.service.api.library;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@FeignClient(value = "library-service", url = "${app.library-url}")
public interface ILibraryServiceClient {
    @PostMapping(value = "/{uuid}",
            consumes = "application/json", produces = "application/json")
    ResponseEntity<?> save(
            @RequestHeader("Authorization") String jwt,
            @PathVariable UUID uuid,
            @RequestParam("from") String from,
            @RequestParam("to") String to);
    @DeleteMapping("/delete/{uuid}")
    ResponseEntity<?> delete(@RequestHeader("Authorization") String jwt,
                             @PathVariable("uuid") UUID uuid);
    @GetMapping("/free")
    ResponseEntity<List<UUID>> getFree(@RequestHeader("Authorization") String jwt);
}
