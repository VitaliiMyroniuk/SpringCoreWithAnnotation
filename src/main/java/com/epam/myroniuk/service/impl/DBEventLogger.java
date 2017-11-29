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
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public void logEvent(Event event) throws IOException {
        jdbcTemplate.update(
                "INSERT INTO events (id, message) VALUES (?, ?)",
                event.getId(),
                event.toString());
    }
}
