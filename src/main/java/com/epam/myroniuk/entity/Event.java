package com.epam.myroniuk.entity;

import org.springframework.beans.factory.annotation.Autowired;
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
    private int id;
    private String msg;
    private Date date;
    private DateFormat df;

    @Autowired
    public Event(Date date, String msg, DateFormat df) {
        this.id = new Random().nextInt(100);
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