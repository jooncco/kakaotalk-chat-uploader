package dev.jooncco.kakaotalkchatuploader.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DateTimeUtilityTests {

    /* ======= Hard coded test data ======= */
    private static final LocalDateTime dateTime =
            LocalDateTime.parse(
                    "2023-11-13 14:43:13.248",
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
    /* ======= End of hard coded test data ======= */

    @Nested
    @DisplayName("getFormattedString 메서드 테스트")
    class GetFormattedStringTests {

        @DisplayName("파일 형식이 주어졌을 때, 포맷에 맞게 리턴한다.")
        @ParameterizedTest
        @CsvSource({"yyyy", "yyyyMMdd", "yyyyMMdd:HH", "yyyyMMdd:mm", "MM", "dd:mm.ss"})
        void getFormattedString_normal_success(String format) {
            // given: 'format' argument

            // when
            String formattedString = DateTimeUtility.getFormattedString(dateTime, format);

            // then
            assertEquals(format.length(), formattedString.length());
        }
    }
}
