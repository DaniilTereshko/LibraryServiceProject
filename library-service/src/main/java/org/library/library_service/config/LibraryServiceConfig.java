package org.library.library_service.config;

import jakarta.validation.Validator;
import org.library.library_service.core.converter.ConversionServiceFactory;
import org.library.library_service.dao.repositories.IRecordRepository;
import org.library.library_service.endpoints.web.util.JwtHandler;
import org.library.library_service.service.api.record.IRecordService;
import org.library.library_service.service.api.user.IUserService;
import org.library.library_service.service.api.user.IUserServiceClient;
import org.library.library_service.service.impl.RecordService;
import org.library.library_service.service.impl.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LibraryServiceConfig {
    @Bean
    public ConversionServiceFactory conversionService(){
        return new ConversionServiceFactory();
    }
    @Bean
    public IRecordService recordService(IRecordRepository recordRepository, Validator validator) {
        return new RecordService(recordRepository, validator);
    }
    @Bean
    public IUserService userService(IUserServiceClient userServiceClient) {
        return new UserService(userServiceClient);
    }
}
