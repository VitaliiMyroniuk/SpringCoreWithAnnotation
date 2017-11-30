package com.epam.myroniuk.config;

import com.epam.myroniuk.entity.EventType;
import com.epam.myroniuk.service.EventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import java.util.*;

/**
 * @author Vitalii Myroniuk
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.epam.myroniuk"})
@PropertySource(value = "classpath:jdbc.properties")
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

    @Value("${jdbc.driverClassName}")
    private String driverClassName;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String userName;

    @Value("${jdbc.password}")
    private String password;

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

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
}
