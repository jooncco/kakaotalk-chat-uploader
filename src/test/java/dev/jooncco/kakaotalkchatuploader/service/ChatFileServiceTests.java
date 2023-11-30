package dev.jooncco.kakaotalkchatuploader.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import dev.jooncco.kakaotalkchatuploader.intf.aws.service.S3Service;

@ExtendWith(MockitoExtension.class)
public class ChatFileServiceTests {

    @Mock private S3Service s3Service;

    @InjectMocks private ChatFileServiceImpl logFileService;

//     /* ======= Hard coded test data ======= */
//     private static final LocalDateTime dateTime =
//             LocalDateTime.parse(
//                     "2023-11-13 14:43:13.248",
//                     DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
//     private static final String efsRootDirectory = "src/test/resources/localstack";
//     private static final Path hourDirectoryPath =
//             Paths.get(efsRootDirectory, AUTH.getName(), "2023", "11", "13", "14");
//     private static final List<File> filesForUpload =
//             List.of(
//                     Paths.get(
//                                     hourDirectoryPath.toString(),
//                                     "rollout-nextsdp-dummy-kic-tvservice-auth-7864fcb86d-aaaaa",
//                                     "202311131420"
//                                             + DEVICE_STAT_LOG_FILE_SUBSTRING
//                                             + "_raw_1"
//                                             + "."
//                                             + LOG.getSlug())
//                             .toFile(),
//                     Paths.get(
//                                     hourDirectoryPath.toString(),
//                                     "rollout-nextsdp-dummy-kic-tvservice-auth-7864fcb86d-bbbbb",
//                                     "202311131420"
//                                             + DEVICE_STAT_LOG_FILE_SUBSTRING
//                                             + "_raw_2"
//                                             + "."
//                                             + LOG.getSlug())
//                             .toFile());
//     private static final List<File> alreadyUploadedLogFiles =
//             List.of(
//                     Paths.get(
//                                     hourDirectoryPath.toString(),
//                                     "rollout-nextsdp-dummy-kic-tvservice-auth-7864fcb86d-aaaaa",
//                                     "202311131420"
//                                             + DEVICE_STAT_LOG_FILE_SUBSTRING
//                                             + "_1_"
//                                             + "."
//                                             + LOG.getSlug()
//                                             + "."
//                                             + GZIP.getSlug())
//                             .toFile(),
//                     Paths.get(
//                                     hourDirectoryPath.toString(),
//                                     "rollout-nextsdp-dummy-kic-tvservice-auth-7864fcb86d-ccccc",
//                                     "202311131420"
//                                             + DEVICE_STAT_LOG_FILE_SUBSTRING
//                                             + "_2_"
//                                             + "."
//                                             + LOG.getSlug()
//                                             + "."
//                                             + GZIP.getSlug())
//                             .toFile());
//     private static final List<File> otherFiles =
//             List.of(
//                     Paths.get(
//                                     hourDirectoryPath.toString(),
//                                     "rollout-nextsdp-dummy-kic-tvservice-auth-7864fcb86d-aaaaa",
//                                     "202311131420file_1.txt")
//                             .toFile(),
//                     Paths.get(
//                                     hourDirectoryPath.toString(),
//                                     "rollout-nextsdp-dummy-kic-tvservice-auth-7864fcb86d-bbbbb",
//                                     "202311131420file_2.lag")
//                             .toFile(),
//                     Paths.get(
//                                     hourDirectoryPath.toString(),
//                                     "rollout-nextsdp-dummy-kic-tvservice-auth-7864fcb86d-ccccc",
//                                     "202311131420deviceStat.lag")
//                             .toFile(),
//                     Paths.get(
//                                     hourDirectoryPath.toString(),
//                                     "rollout-nextsdp-dummy-kic-tvservice-auth-7864fcb86d-bbbbb",
//                                     "202311131420file_4_without_extension")
//                             .toFile());
//     /* ======= End of hard coded test data ======= */

//     @BeforeEach
//     public void beforeEach() {
//         ReflectionTestUtils.setField(logFileService, "logFileStoreRootPath", efsRootDirectory);
//         ReflectionTestUtils.setField(logFileService, "logFileTtl", 40);
//     }

//     @AfterEach
//     public void afterEach() {
//         try {
//             FileUtils.deleteDirectory(Paths.get(efsRootDirectory).toFile());
//         } catch (IOException ex) {
//             // DO NOTHING
//         }
//     }


//     @Nested
//     @DisplayName("deleteOldFiles 메서드 테스트")
//     class DeleteOldFilesTests {
//         @DisplayName("40일 이상 지난 디렉토리가 정상 삭제된다.")
//         @Test
//         void deleteOldFiles_normal_success() {
//             // given
//             List<File> directories =
//                     List.of(
//                             // kept
//                             Paths.get(efsRootDirectory, AUTH.getName(), "2023", "11", "12", "14")
//                                     .toFile(),
//                             Paths.get(efsRootDirectory, AUTH.getName(), "2023", "11", "09", "02")
//                                     .toFile(),
//                             Paths.get(efsRootDirectory, AUTH.getName(), "2023", "10", "31", "07")
//                                     .toFile(),
//                             Paths.get(efsRootDirectory, AUTH.getName(), "2023", "10", "10", "14")
//                                     .toFile(),
//                             Paths.get(efsRootDirectory, AUTH.getName(), "2023", "10", "06", "14")
//                                     .toFile(),
//                             Paths.get(efsRootDirectory, AUTH.getName(), "2023", "10", "05", "14")
//                                     .toFile(),
//                             Paths.get(efsRootDirectory, AUTH.getName(), "2023", "10", "04", "14")
//                                     .toFile(),
//                             Paths.get(efsRootDirectory, AUTH.getName(), "2023", "10", "04", "14")
//                                     .toFile(),
//                             // deleted
//                             Paths.get(efsRootDirectory, AUTH.getName(), "2023", "10", "03", "23")
//                                     .toFile(),
//                             Paths.get(efsRootDirectory, AUTH.getName(), "2023", "10", "03", "15")
//                                     .toFile(),
//                             Paths.get(efsRootDirectory, AUTH.getName(), "2023", "10", "03", "00")
//                                     .toFile(),
//                             Paths.get(efsRootDirectory, AUTH.getName(), "2023", "09", "30", "14")
//                                     .toFile(),
//                             Paths.get(efsRootDirectory, AUTH.getName(), "2023", "08", "29", "02")
//                                     .toFile());
//             for (File directory : directories) {
//                 directory.mkdirs();
//             }

//             // when
//             logFileService.deleteOldFiles(dateTime);

//             // then
//             int kept = 0, deleted = 0;
//             for (File deletedDirectory : directories) {
//                 if (deletedDirectory.exists()) ++kept;
//                 else ++deleted;
//             }
//             assertEquals(8, kept);
//             assertEquals(5, deleted);
//         }
//     }

//     @Nested
//     @DisplayName("getMonthSourceDirectory 메서드 테스트")
//     class GetMonthSourceDirectoryTests {
//         @DisplayName("월 단위 디렉토리가 정상 리턴된다.")
//         @Test
//         void getMonthSourceDirectory_normal_success() {
//             // given
//             // NOTHING

//             // when
//             File monthSourceDirectory = logFileService.getMonthSourceDirectory(dateTime);
//             String path = monthSourceDirectory.getPath();

//             // then
//             assertEquals(
//                     Paths.get(efsRootDirectory, AUTH.getName(), "2023", "11").toString(), path);
//         }
//     }

//     @Nested
//     @DisplayName("getDaySourceDirectory 메서드 테스트")
//     class GetDaySourceDirectoryTests {
//         @DisplayName("일 단위 디렉토리가 정상 리턴된다.")
//         @Test
//         void getDaySourceDirectory_normal_success() {
//             // given
//             // NOTHING

//             // when
//             File daySourceDirectory = logFileService.getDaySourceDirectory(dateTime);
//             String path = daySourceDirectory.getPath();

//             // then
//             assertEquals(
//                     Paths.get(efsRootDirectory, AUTH.getName(), "2023", "11", "13").toString(),
//                     path);
//         }
//     }

//     @Nested
//     @DisplayName("getHourSourceDirectory 메서드 테스트")
//     class GetHourSourceDirectoryTests {
//         @DisplayName("시간 단위 디렉토리가 정상 리턴된다.")
//         @Test
//         void getHourSourceDirectory_normal_success() {
//             // given
//             // NOTHING

//             // when
//             File hourSourceDirectory = logFileService.getHourSourceDirectory(dateTime);
//             String path = hourSourceDirectory.getPath();

//             // then
//             assertEquals(
//                     Paths.get(efsRootDirectory, AUTH.getName(), "2023", "11", "13", "14")
//                             .toString(),
//                     path);
//         }
//     }
}
