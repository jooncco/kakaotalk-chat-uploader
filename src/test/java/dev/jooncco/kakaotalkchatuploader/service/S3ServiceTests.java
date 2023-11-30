package dev.jooncco.kakaotalkchatuploader.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.amazonaws.SdkClientException;

import dev.jooncco.kakaotalkchatuploader.intf.aws.provider.S3ClientProvider;
import dev.jooncco.kakaotalkchatuploader.intf.aws.service.S3ServiceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
public class S3ServiceTests {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private S3ClientProvider amazonS3ClientProvider;

    @InjectMocks private S3ServiceImpl s3Service;

    // /* ======= Hard coded test data ======= */
    // private static final String efsRootDirectory = "src/test/resources/localstack";
    // private static final Path parentDirectoryPath =
    //         Paths.get(
    //                 efsRootDirectory,
    //                 AUTH.getName(),
    //                 "2023",
    //                 "11",
    //                 "13",
    //                 "14",
    //                 "rollout-nextsdp-dev-kic-tvservice-auth-7864fcb86d-lm55h");
    // private static final List<File> filesForUpload =
    //         List.of(
    //                 Paths.get(
    //                                 parentDirectoryPath.toString(),
    //                                 "202311131420"
    //                                         + DEVICE_STAT_LOG_FILE_SUBSTRING
    //                                         + "_1_"
    //                                         + "."
    //                                         + LOG.getSlug()
    //                                         + "."
    //                                         + GZIP.getSlug())
    //                         .toFile(),
    //                 Paths.get(
    //                                 parentDirectoryPath.toString(),
    //                                 "202311131420"
    //                                         + DEVICE_STAT_LOG_FILE_SUBSTRING
    //                                         + "_2_"
    //                                         + "."
    //                                         + LOG.getSlug()
    //                                         + "."
    //                                         + GZIP.getSlug())
    //                         .toFile());
    // /* ======= End of hard coded test data ======= */

    // @BeforeEach
    // public void beforeEach() {
    //     ReflectionTestUtils.setField(s3Service, "s3BucketName", "test-bucket");
    // }

    // @AfterEach
    // public void afterEach() {
    //     try {
    //         FileUtils.deleteDirectory(Paths.get(efsRootDirectory).toFile());
    //     } catch (IOException ex) {
    //         // DO NOTHING
    //     }
    // }

    // @Nested
    // @DisplayName("uploadFiles 메서드 테스트")
    // class UploadFilesTests {

    //     @DisplayName("deleteOnFail = true 이고 업로드 중에 실패할 경우 해당 파일을 삭제한다.")
    //     @Test
    //     void uploadFiles_failOnUpload_deleteFiles() {
    //         // given
    //         File directory = parentDirectoryPath.toFile();
    //         directory.mkdirs();
    //         try {
    //             // 업로드 대상인 파일들을 생성
    //             for (File file : filesForUpload) {
    //                 file.createNewFile();
    //             }
    //         } catch (IOException ex) {
    //             // DO NOTHING
    //         }
    //         when(amazonS3ClientProvider
    //                         .getClient()
    //                         .putObject(anyString(), anyString(), (File) any()))
    //                 .thenThrow(new SdkClientException("intended exception"));

    //         // when
    //         s3Service.uploadFiles(filesForUpload, true);

    //         // then
    //         for (File file : filesForUpload) {
    //             assertFalse(file.exists());
    //         }
    //     }

    //     @DisplayName("deleteOnFail = true 이지만 업로드가 정상적으로 성공할 경우 파일들을 삭제하지 않는다.")
    //     @Test
    //     void uploadFiles_normal_keepFiles() {
    //         // given
    //         File directory = parentDirectoryPath.toFile();
    //         directory.mkdirs();
    //         try {
    //             // 업로드 대상인 파일들을 생성
    //             for (File file : filesForUpload) {
    //                 file.createNewFile();
    //             }
    //         } catch (IOException ex) {
    //             // DO NOTHING
    //         }

    //         // when
    //         s3Service.uploadFiles(filesForUpload, true);

    //         // then
    //         for (File file : filesForUpload) {
    //             assertTrue(file.exists());
    //         }
    //     }

    //     @DisplayName("deleteOnFail = false 이고 업로드 중에 실패할 경우 해당 파일을 삭제하지 않는다.")
    //     @Test
    //     void uploadFiles_failOnUploadButDeleteOnFailIsFalse_keepFiles() {
    //         // given
    //         File directory = parentDirectoryPath.toFile();
    //         directory.mkdirs();
    //         try {
    //             // 업로드 대상인 파일들을 생성
    //             for (File file : filesForUpload) {
    //                 file.createNewFile();
    //             }
    //         } catch (IOException ex) {
    //             // DO NOTHING
    //         }
    //         when(amazonS3ClientProvider
    //                         .getClient()
    //                         .putObject(anyString(), anyString(), (File) any()))
    //                 .thenThrow(new SdkClientException("intended exception"));

    //         // when
    //         s3Service.uploadFiles(filesForUpload, false);

    //         // then
    //         for (File file : filesForUpload) {
    //             assertTrue(file.exists());
    //         }
    //     }
    // }
}
