package dev.jooncco.kakaotalkchatuploader.intf.aws.service;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3Client;

import dev.jooncco.kakaotalkchatuploader.intf.aws.provider.S3ClientProvider;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3ServiceImpl implements S3Service {

    @Value("${aws.s3.bucketName}")
    private String s3BucketName;

    private final S3ClientProvider amazonS3ClientProvider;

    /**
     * 인자로 받은 파일들을 S3 bucket 에 업로드 합니다. deleteOnFail argument 를 통해 업로드 실패한 파일을 삭제할지 제어할 수 있습니다.
     *
     * @param files
     * @param deleteOnFail
     */
    @Override
    public void uploadFiles(List<File> files, boolean deleteOnFail) {
        AmazonS3Client s3Client = amazonS3ClientProvider.getClient();

        for (File file : files) {
            try {
                s3Client.putObject(s3BucketName, getDeviceStatObjectKey(file), file);
                log.debug("Uploaded: {}", file.getName());
            } catch (SdkClientException ex) {
                log.error("Failed to upload: {}", file.getName(), ex);
                if (deleteOnFail) {
                    try {
                        Files.delete(file.toPath());
                        log.info("Deleted file: {}", file.getName());
                    } catch (IOException ignored) {
                        log.info("Failed to delete file: {}", file.getName());
                    }
                }
            }
        }
    }

    private String getDeviceStatObjectKey(File file) {
        final int TIMESTAMP_LEN = 8;
        String yyyyMMdd;
        Pattern pattern = Pattern.compile("^([0-9]{8}).*");
        Matcher matcher = pattern.matcher(file.getName());
        if (matcher.find()) {
            yyyyMMdd = matcher.group(1);
        } else {
            yyyyMMdd = file.getName().substring(0, TIMESTAMP_LEN);
        }
        return Paths.get("deviceStat", file.getParentFile().getName(), yyyyMMdd, file.getName())
                .toString();
    }
}
