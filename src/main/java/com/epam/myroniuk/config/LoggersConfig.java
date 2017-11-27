package com.epam.myroniuk.config;

import com.epam.myroniuk.entity.EventType;
import com.epam.myroniuk.service.EventLogger;
import com.epam.myroniuk.service.impl.CombinedEventLogger;
import com.epam.myroniuk.service.impl.ConsoleEventLogger;
import com.epam.myroniuk.service.impl.FileEventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    public LoggersConfig(ConsoleEventLogger consoleEventLogger,
                         FileEventLogger fileEventLogger, CombinedEventLogger combinedEventLogger) {
        this.consoleEventLogger = consoleEventLogger;
        this.fileEventLogger = fileEventLogger;
        this.combinedEventLogger = combinedEventLogger;
    }

    @Bean
    public List<EventLogger> listLoggers(){
        ArrayList<EventLogger> result = new ArrayList<>();
        result.add(consoleEventLogger);
        result.add(fileEventLogger);
        return result;
    }

    @Bean
    public Map<EventType, EventLogger> loggers() {
        Map<EventType, EventLogger> result = new HashMap<>();
        result.put(EventType.INFO, consoleEventLogger);
        result.put(EventType.ERROR, combinedEventLogger);
        return result;
    }
}
