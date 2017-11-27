package com.epam.myroniuk.config;

import com.epam.myroniuk.entity.Event;
import com.epam.myroniuk.entity.EventType;
import com.epam.myroniuk.service.EventLogger;
import com.epam.myroniuk.service.impl.CombinedEventLogger;
import com.epam.myroniuk.service.impl.ConsoleEventLogger;
import com.epam.myroniuk.service.impl.FileEventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vitalii Myroniuk
 */
@Configuration
public class LoggersConfig {
    private EventLogger consoleEventLogger;
    private EventLogger fileEventLogger;
    private EventLogger combinedEventLogger;

    @Autowired
    @Lazy(value = true)
    public LoggersConfig(ConsoleEventLogger consoleEventLogger,
                         FileEventLogger fileEventLogger,
                         CombinedEventLogger combinedEventLogger) {
        this.consoleEventLogger = consoleEventLogger;
        this.fileEventLogger = fileEventLogger;
        this.combinedEventLogger = combinedEventLogger;
    }

    // Beans to inject into CacheFileEventLogger object
    @Bean
    public String fileName() {
        return "src/main/resources/logs.txt";
    }

    @Bean
    public int cacheSize() {
        return 3;
    }

    @Bean
    public List<Event> cache() {
        return new ArrayList<>();
    }

    // Bean to inject into CombinedEventLogger object
    @Bean
    public List<EventLogger> listOfLoggers(){
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
