package dev.jooncco.kakaotalkchatuploader.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CommonConstants {

    @RequiredArgsConstructor
    public enum FileExtension {
        TEXT("txt");

        @Getter private final String slug;
    }

    public static class OpenChat {
        public static String TITLE_SUFFIX= "님과 카카오톡 대화";
        public static String NEW_DAY_INDICATOR= "---------------";
        public static String TIME_FORMAT= "a h:mm";
        public static String DATE_FORMAT= "yyyy년 M월 d일 E";
    }

    public static final String S3_UPLOAD_DATETIME_FORMAT= "yyyy_MM_dd_HH_mm";
    public static final String S3_UPLOAD_SEQUENCE_FORMAT= "%09d";
    public static final int KOREA_TIMEZONE_DIFF= 9;
}
