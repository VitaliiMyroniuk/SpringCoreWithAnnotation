package com.epam.myroniuk.service;

import com.epam.myroniuk.entity.Event;

import java.io.IOException;

/**
 * @author Vitalii Myroniuk
 */
public interface EventLogger {
    void logEvent(Event event) throws IOException;
}
