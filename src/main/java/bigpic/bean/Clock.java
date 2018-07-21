package bigpic.bean;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Clock {
    String dateTimeFormat = "HH:mm:ss";

    public String getCurrentDay() {
        return LocalDateTime.now().getDayOfWeek().toString();
    }

    public String getCurrentFormatedTime() {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(dateTimeFormat);
        String formatedTime = sdf.format(now);
        return formatedTime;
    }

    public String getCurrnetDate() {
        return LocalDate.now().toString();
    }

    public String getDateTimeFormat() {
        return dateTimeFormat;
    }

    public void setDateTimeFormat(String dateTimeFormat) {
        this.dateTimeFormat = dateTimeFormat;
    }
}
