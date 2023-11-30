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
}
