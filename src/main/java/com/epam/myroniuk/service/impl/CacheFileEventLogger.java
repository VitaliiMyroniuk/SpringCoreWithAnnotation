package com.epam.myroniuk.service.impl;

import com.epam.myroniuk.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
@Component
public class CacheFileEventLogger extends FileEventLogger {
    @Value("3")
    private int cacheSize;

    @Value("#{new java.util.ArrayList()}")
    private List<Event> cache;

    public CacheFileEventLogger(String fileName, int cacheSize, List<Event> cache) {
        super(fileName);
        this.cacheSize = cacheSize;
        this.cache = cache;
    }

    public CacheFileEventLogger() {
    }

    @PreDestroy
    private void destroy() throws IOException {
        if (!cache.isEmpty()) {
            writeEventsFromCache();
        }
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

    public int getCacheSize() {
        return cacheSize;
    }

    public void setCacheSize(int cacheSize) {
        this.cacheSize = cacheSize;
    }
}
