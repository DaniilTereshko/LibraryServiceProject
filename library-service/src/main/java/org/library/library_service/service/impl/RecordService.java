package org.library.library_service.service.impl;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.library.base_package.dto.library_service.RecordCreateDTO;
import org.library.library_service.core.exception.IllegalDatePeriodException;
import org.library.library_service.core.exception.IncorrectDataException;
import org.library.library_service.core.exception.NotFoundException;
import org.library.library_service.core.exception.VersionsMatchException;
import org.library.library_service.dao.entity.Record;
import org.library.library_service.dao.repositories.IRecordRepository;
import org.library.library_service.service.api.record.IRecordService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class RecordService implements IRecordService {
    private static final String VERSIONS_MATCH_ERROR = "Версии не совпадают";
    private static final String NOT_FOUND = "Запись не найдена";
    private static final String FROM_ERROR = "Дата from не может быть раньше текущей даты";
    private static final String TO_ERROR = "Дата to не может быть раньше даты from";
    private static final String MIN_DURATION_ERROR = "Разница между from' и to должна быть минимум 1 час";
    private static final String MAX_DURATION_ERROR = "Разница между from и to не может быть больше недели";
    private static final String INCORRECT_DATA = "Неверные данные. Попробуйте еще раз";
    private final IRecordRepository recordRepository;
    private final Validator validator;

    public RecordService(IRecordRepository recordRepository, Validator validator) {
        this.recordRepository = recordRepository;
        this.validator = validator;
    }
    @Transactional
    @Override
    public void save(RecordCreateDTO item) {
        this.validate(item);
        LocalDateTime from = item.getFrom();
        LocalDateTime to = item.getTo();
        LocalDateTime currentDate = LocalDateTime.now();

        if (from.isBefore(currentDate)) {
            throw new IllegalDatePeriodException(FROM_ERROR);
        }
        if (to.isBefore(from)) {
            throw new IllegalDatePeriodException(TO_ERROR);
        }
        if (Duration.between(from, to).toHours() < 1) {
            throw new IllegalDatePeriodException(MIN_DURATION_ERROR);
        }
        if (Duration.between(from, to).toDays() > 7) {
            throw new IllegalDatePeriodException(MAX_DURATION_ERROR);
        }

        Record record = new Record(item.getBook(), item.getFrom(), item.getTo());
        this.recordRepository.save(record);
    }
    @Transactional(readOnly = true)
    @Override
    public List<Record> getFree() {
        LocalDateTime currentDate = LocalDateTime.now();
        return this.recordRepository.findFreeRecords(currentDate);
    }
    @Transactional(readOnly = true)
    @Override
    public Page<Record> get(int page, int size) {
        if(page < 0 || size < 0){
            throw new IncorrectDataException(INCORRECT_DATA);
        }
        PageRequest request = PageRequest.of(page, size);
        return this.recordRepository.findAll(request);
    }
    @Transactional
    @Override
    public void delete(UUID uuid){
        Record record = this.recordRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND));
        this.recordRepository.delete(record);
    }
    private <T> void validate(T item) {
        Set<ConstraintViolation<T>> constraintViolations = this.validator.validate(item);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }
}
