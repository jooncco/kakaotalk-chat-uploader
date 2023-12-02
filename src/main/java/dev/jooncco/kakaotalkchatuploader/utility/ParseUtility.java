package dev.jooncco.kakaotalkchatuploader.utility;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static dev.jooncco.kakaotalkchatuploader.constants.CommonConstants.OpenChat;

@UtilityClass
public class ParseUtility {

    public static LocalDate parseLocalDate(String line) {
        if (line == null || !line.contains(OpenChat.NEW_DAY_INDICATOR)) return null;
        int from= line.indexOf(' ');
        int to= line.lastIndexOf("요일");
        String dateString= line.substring(from, to).strip();
        return LocalDate.parse(
                dateString,
                DateTimeFormatter.ofPattern(OpenChat.DATE_FORMAT).withLocale(Locale.KOREA));
    }

    public static LocalTime parseLocalTime(String line) {
        if (line == null || !line.contains("[") || !line.contains("]")) return null;
        String lineWithoutSpeaker= line.substring(line.indexOf("]")+1);
        int from= lineWithoutSpeaker.indexOf('[')+1;
        int to= lineWithoutSpeaker.indexOf(']');

        String timestampString= lineWithoutSpeaker.substring(from, to).strip();
        return LocalTime.parse(
                timestampString,
                DateTimeFormatter.ofPattern(OpenChat.TIME_FORMAT).withLocale(Locale.KOREA));
    }

    public static String parseSpeaker(String line) {
        if (line == null || !line.startsWith("[") || !line.contains("]")) return null;
        int from= line.indexOf('[')+1;
        int to= line.indexOf(']');
        return line.substring(from, to);
    }

    public static String parseSentence(String line) {
        if (line == null) return null;
        if (!line.startsWith("[") || !line.contains("]")) return line;
        int from= line.lastIndexOf(']')+1;
        return line.substring(from).strip();
    }
}
