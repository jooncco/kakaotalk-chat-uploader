package dev.jooncco.kakaotalkchatuploader.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DateTimeUtility {

    /**
     * LocalDateTime 타입의 인자를 받아 formatting 후 리턴하는 유틸리티 메소드.
     *
     * @param dateTime
     * @param format
     * @return
     */
    public static String getFormattedString(LocalDateTime dateTime, String format) {
        return dateTime.format(DateTimeFormatter.ofPattern(format));
    }
}
