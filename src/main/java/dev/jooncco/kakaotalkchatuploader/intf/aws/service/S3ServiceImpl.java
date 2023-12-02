package dev.jooncco.kakaotalkchatuploader.intf.aws.service;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3Client;

import dev.jooncco.kakaotalkchatuploader.constants.CommonConstants;
import dev.jooncco.kakaotalkchatuploader.intf.aws.provider.S3ClientProvider;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dev.jooncco.kakaotalkchatuploader.utility.DateTimeUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static dev.jooncco.kakaotalkchatuploader.constants.CommonConstants.S3_UPLOAD_DATETIME_FORMAT;
import static dev.jooncco.kakaotalkchatuploader.constants.CommonConstants.S3_UPLOAD_SEQUENCE_FORMAT;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3ServiceImpl implements S3Service {

    @Value("${aws.s3.bucketName}")
    private String s3BucketName;

    private final S3ClientProvider amazonS3ClientProvider;

    /**
     * Upload a file to S3 bucket.
     *
     * @param file
     */
    @Override
    public void uploadFile(File file) {
        AmazonS3Client s3Client = amazonS3ClientProvider.getClient();

        try {
            s3Client.putObject(s3BucketName, file.getName(), file);
            log.debug("Uploaded: {}", file.getName());
        } catch (SdkClientException ex) {
            log.error("Failed to upload: " + file.getName(), ex);
        }
    }

    @Override
    public String generateAmazonS3ObjectKey(String openChatTitle, LocalDateTime dateTime, int sequence) {
        String dateTimeString= DateTimeUtility.getFormattedString(dateTime, S3_UPLOAD_DATETIME_FORMAT);
        return openChatTitle + "_"
                + dateTimeString + "_"
                + String.format(S3_UPLOAD_SEQUENCE_FORMAT, sequence)
                + "." + CommonConstants.FileExtension.TEXT.getSlug();
    }
}
