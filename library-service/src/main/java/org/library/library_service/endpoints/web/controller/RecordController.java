package org.library.library_service.endpoints.web.controller;

import org.library.base_package.dto.PageDTO;
import org.library.base_package.dto.library_service.RecordDTO;
import org.library.library_service.dao.entity.Record;
import org.library.library_service.endpoints.web.util.PageConverter;
import org.library.library_service.service.api.record.IRecordService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/library")
public class RecordController {
    private final IRecordService recordService;
    private final PageConverter pageConverter;

    public RecordController(IRecordService recordService, PageConverter pageConverter, ConversionService conversionService) {
        this.recordService = recordService;
        this.pageConverter = pageConverter;
    }

    @GetMapping("/all")
    public ResponseEntity<PageDTO<RecordDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ){
        Page<Record> records = this.recordService.get(page, size);
        PageDTO<RecordDTO> recordPageDTO = this.pageConverter.convertPageToDTO(records, RecordDTO.class);
        return new ResponseEntity<>(recordPageDTO, HttpStatus.OK);
    }
}
