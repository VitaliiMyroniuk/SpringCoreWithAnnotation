package com.epam.myroniuk.service.impl;

import com.epam.myroniuk.entity.Event;
import com.epam.myroniuk.service.EventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
@Component
public class CombinedEventLogger implements EventLogger {
    @Autowired
    private List<EventLogger> listOfLoggers;

    public CombinedEventLogger() {
    }

    public void setListOfLoggers(List<EventLogger> listOfLoggers) {
        this.listOfLoggers = listOfLoggers;
    }

    @Override
    public void logEvent(Event event) throws IOException {
        for (EventLogger logger : listOfLoggers) {
            logger.logEvent(event);
        }
    }
}
