package org.library.book_service.config;

import org.library.book_service.core.converter.BookToBookDTOConverter;
import org.library.book_service.core.exception.formatter.LocalDateTimeToMilliFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new BookToBookDTOConverter());
        registry.addFormatter(new LocalDateTimeToMilliFormatter());
    }
}
