package ru.alex.generateurls.model.response;


import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class Info {

    private static Info info;

    private String creator;

    private ZonedDateTime todayDate;

    private Integer countOfFunction;

    private Info() {
        creator = "Alex";
        todayDate = ZonedDateTime.now();
        countOfFunction = 2;
    }

    public synchronized static Info getInstant() {
        if (info == null) {
            info = new Info();
        }
        return info;
    }



}
