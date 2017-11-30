package com.epam.myroniuk;

import com.epam.myroniuk.config.AppConfig;
import com.epam.myroniuk.entity.Client;
import com.epam.myroniuk.entity.Event;
import com.epam.myroniuk.entity.EventType;
import com.epam.myroniuk.service.EventLogger;
import com.epam.myroniuk.service.impl.DBEventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Map;

/**
 * @author Vitalii Myroniuk
 */
@Component
public class App {
    @Autowired
    @Qualifier("client")
    private Client client;

    @Autowired
    @Qualifier("loggers")
    private Map<EventType, EventLogger> loggers;

    public static void main(String[] args) throws IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        App app = (App) context.getBean("app");
        Event event = (Event) context.getBean("event");
        app.someAdd(EventType.ERROR, event);

        new DBEventLogger().logEvent(event);

        ((ConfigurableApplicationContext) context).close();
    }

    private void someAdd(EventType eventType, Event event) throws IOException {
        EventLogger eventLogger = loggers.get(eventType);
        String message = event.getMessage().replaceAll(client.getId(), client.getFullName());
        event.setMessage(message);
        eventLogger.logEvent(event);
    }
}
