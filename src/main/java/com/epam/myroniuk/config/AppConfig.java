package com.epam.myroniuk.config;

import com.epam.myroniuk.entity.EventType;
import com.epam.myroniuk.service.EventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import java.util.*;

/**
 * @author Vitalii Myroniuk
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.epam.myroniuk"})
public class AppConfig {
    @Autowired
    @Qualifier("consoleEventLogger")
    private EventLogger consoleEventLogger;

    @Autowired
    @Qualifier("fileEventLogger")
    private EventLogger fileEventLogger;

    @Autowired
    @Qualifier("combinedEventLogger")
    private EventLogger combinedEventLogger;

    // Bean to inject into CombinedEventLogger object
    @Bean
    public List<EventLogger> eventLoggers() {
        ArrayList<EventLogger> result = new ArrayList<>();
        result.add(consoleEventLogger);
        result.add(fileEventLogger);
        return result;
    }

    // Bean to inject into App object
    @Bean
    public Map<EventType, EventLogger> loggers() {
        Map<EventType, EventLogger> result = new HashMap<>();
        result.put(EventType.INFO, consoleEventLogger);
        result.put(EventType.ERROR, combinedEventLogger);
        return result;
    }
}
