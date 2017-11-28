package com.epam.myroniuk.service.impl;

import com.epam.myroniuk.entity.Event;
import com.epam.myroniuk.service.EventLogger;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

/**
 * @author Vitalii Myroniuk
 */
@Component
@PropertySource(value = "data.properties")
public class FileEventLogger implements EventLogger {
    @Value("${fileName}")
    private String fileName;
    private File file;

    @PostConstruct
    private void init() throws IOException {
        this.file = new File(fileName);
    }

    @Override
    public void logEvent(Event event) throws IOException {
        FileUtils.writeStringToFile(file, event.toString()+"\n", "UTF-8", true);
    }
}
