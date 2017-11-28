package com.epam.myroniuk.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author Vitalii Myroniuk
 */
@Component
@Scope(value = "prototype")
public class Event {

    @Value("#{new java.util.Random().nextInt(100)}")
    private int id;

    @Value("Some message for client 1")
    private String msg;

    @Value("#{new java.util.Date()}")
    private Date date;

    @Value("#{T(java.text.DateFormat).getDateTimeInstance()}")
    private DateFormat df;

    public Event() {
    }

    public Event(int id, Date date, String msg, DateFormat df) {
        this.id = id;
        this.msg = msg;
        this.date = date;
        this.df = df;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date=" + df.format(date) +
                '}';
    }
}
