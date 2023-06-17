package utils.dateTime;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeUtil {

    public static LocalDate getDate(){
        LocalDate localDate = LocalDate.now();
        return(localDate);
    }

    public static String getTime(){
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm:ss a");
        String formattedTime = currentTime.format(formatter);
        return(formattedTime);
    }

    public static Long elapsed(LocalDate start, LocalDate end){
        long difference = ChronoUnit.YEARS.between(start, end);
        return(difference);
    }

    public static Long elapsedPresent(LocalDate from){
        long difference = ChronoUnit.YEARS.between(from, LocalDate.now());
        return(difference);
    }

    public static LocalDate toLocalDate(Date date){
        return(date.toLocalDate());
    }
}
