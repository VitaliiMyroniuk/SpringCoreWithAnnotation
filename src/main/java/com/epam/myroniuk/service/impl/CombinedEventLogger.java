package com.epam.myroniuk.service.impl;

import com.epam.myroniuk.entity.Event;
import com.epam.myroniuk.service.EventLogger;
import java.io.IOException;
import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
public class CombinedEventLogger implements EventLogger {
    private List<EventLogger> loggers;

    public void setLoggers(List<EventLogger> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) throws IOException {
        for (EventLogger logger : loggers) {
            logger.logEvent(event);
        }
    }
}
