package com.epam.myroniuk.service.impl;

import com.epam.myroniuk.entity.Event;
import com.epam.myroniuk.service.EventLogger;

/**
 * @author Vitalii Myroniuk
 */
public class ConsoleEventLogger implements EventLogger {
    @Override
    public void logEvent(Event event) {
        System.out.println(event.toString());
    }
}
