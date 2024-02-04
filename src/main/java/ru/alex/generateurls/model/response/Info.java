package ru.alex.generateurls.model.response;


import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class Info {

    private static Info info;

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

    public String creator;

    public ZonedDateTime todayDate;

    public Integer countOfFunction;

}
