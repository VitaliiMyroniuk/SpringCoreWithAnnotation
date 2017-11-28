package com.epam.myroniuk.service.impl;

import com.epam.myroniuk.entity.Event;
import com.epam.myroniuk.service.EventLogger;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

/**
 * @author Vitalii Myroniuk
 */
@Component
public class FileEventLogger implements EventLogger {
    @Value("src/main/resources/logs.txt")
    private String fileName;
    private File file;

    public FileEventLogger() {
    }

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    @PostConstruct
    private void init() throws IOException {
        this.file = new File(fileName);
    }

    @Override
    public void logEvent(Event event) throws IOException {
        FileUtils.writeStringToFile(file, event.toString()+"\n", "UTF-8", true);
    }
}
