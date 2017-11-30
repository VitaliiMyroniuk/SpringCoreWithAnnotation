package com.epam.myroniuk.service.impl;

import com.epam.myroniuk.entity.Event;
import com.epam.myroniuk.service.EventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.io.IOException;

/**
 * @author Vitalii_Myroniuk
 */
@Component
public class DBEventLogger implements EventLogger {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void logEvent(Event event) throws IOException {
        System.out.println(jdbcTemplate == null);
        jdbcTemplate.update(
                "INSERT INTO spring_core.events (id, message) VALUES (?, ?)",
                event.getId(),
                event.toString());
    }
}
