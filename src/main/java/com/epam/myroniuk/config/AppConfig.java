package com.epam.myroniuk.config;

import org.springframework.context.annotation.*;
import java.text.DateFormat;
import java.util.*;

/**
 * @author Vitalii Myroniuk
 */
@Configuration
@ComponentScan(basePackages = {"com.epam.myroniuk"})
@Import(LoggersConfig.class)
public class AppConfig {

    // Beans to inject into Event object
    @Bean
    public int id() {
        return new Random().nextInt(100);
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
    public DateFormat dateFormat() {
        return DateFormat.getDateTimeInstance();
    }
}
