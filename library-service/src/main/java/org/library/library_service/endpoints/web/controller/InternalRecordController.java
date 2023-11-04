package org.library.library_service.endpoints.web.controller;

import org.library.base_package.dto.library_service.RecordCreateDTO;
import org.library.library_service.dao.entity.Record;
import org.library.library_service.service.api.record.IRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/internal/library")
public class InternalRecordController {
    private final IRecordService recordService;

    public InternalRecordController(IRecordService recordService) {
        this.recordService = recordService;
    }

    @PostMapping(value = "/{uuid}",
            consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> save(
            @PathVariable UUID uuid,
            @RequestParam("from") LocalDateTime from,
            @RequestParam("to") LocalDateTime to){
        RecordCreateDTO recordCreateDTO = new RecordCreateDTO(uuid, from, to);
        this.recordService.save(recordCreateDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<?> delete(@PathVariable("uuid") UUID uuid){
        this.recordService.delete(uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/free")
    public ResponseEntity<List<UUID>> getFree(){
        List<UUID> uuids = this.recordService.getFree().stream().map(Record::getUuid).toList();
        return new ResponseEntity<>(uuids, HttpStatus.OK);
    }
}
