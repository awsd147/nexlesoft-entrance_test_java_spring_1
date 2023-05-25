package javaSpringEntranceExam.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.joda.time.DateTime;

public class DateTimeUtils {

    public static Date addDateSeconds(Date date, int seconds) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusSeconds(seconds).toDate();
    }

    public static String converDateTime(Date now, int refreshExpiration ) {
        Date expiresIn = DateTimeUtils.addDateSeconds(now, refreshExpiration);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(expiresIn);
    }

}