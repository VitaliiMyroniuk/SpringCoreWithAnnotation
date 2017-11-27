package com.epam.myroniuk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import java.text.DateFormat;
import java.util.*;

/**
 * @author Vitalii Myroniuk
 */
@Configuration
@ComponentScan(basePackages = {"com.epam.myroniuk"})
@Import(LoggersConfig.class)
public class AppConfig {
    @Bean
    public DateFormat dateFormat() {
        return DateFormat.getDateTimeInstance();
    }

    @Bean
    public Date date() {
        return new Date();
    }

    @Bean
    public String message() {
        return "Some message for client 1";
    }

    @Bean
    public Integer cacheSize() {
        return 3;
    }
}
