package org.library.library_service.core.converter;

import org.library.base_package.dto.library_service.RecordDTO;
import org.library.library_service.dao.entity.Record;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class RecordToRecordDTOConverter implements Converter<Record, RecordDTO> {
    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    @Override
    public RecordDTO convert(Record source) {
        return new RecordDTO(source.getUuid(), source.getFrom().format(FORMATTER), source.getTo().format(FORMATTER),
                source.getCreateDate().toInstant(ZoneOffset.UTC).toEpochMilli(),
                source.getUpdateDate().toInstant(ZoneOffset.UTC).toEpochMilli());
    }
}
