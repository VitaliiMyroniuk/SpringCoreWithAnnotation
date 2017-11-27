package com.epam.myroniuk.service.impl;

import com.epam.myroniuk.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
@Component
public class CacheFileEventLogger extends FileEventLogger {
    private int cacheSize;
    private List<Event> cache;

    @Autowired
    public CacheFileEventLogger(String fileName, int cacheSize, List<Event> cache) {
        super(fileName);
        this.cacheSize = cacheSize;
        this.cache = cache;
    }

    @Override
    public void logEvent(Event event) throws IOException {
        cache.add(event);
        if (cache.size() == cacheSize) {
            writeEventsFromCache();
            cache.clear();
        }
    }

    private void writeEventsFromCache() throws IOException {
        for (Event event : cache) {
            super.logEvent(event);
        }
    }

    @PreDestroy
    private void destroy() throws IOException {
        if (!cache.isEmpty()) {
            writeEventsFromCache();
        }
    }
}
