package com.epam.myroniuk.service.impl;

import com.epam.myroniuk.entity.Event;
import com.epam.myroniuk.service.EventLogger;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    private String fileName;
    private File file;

    @Autowired
    public FileEventLogger(@Qualifier(value = "fileName") String fileName) {
        this.fileName = fileName;
    }

    @PostConstruct
    private void init() throws IOException {
        this.file = new File(fileName);
        if (!file.canWrite()) {
            throw new IOException();
        }
    }

    @Override
    public void logEvent(Event event) throws IOException {
        FileUtils.writeStringToFile(file, event.toString()+"\n", "UTF-8", true);
    }
}
