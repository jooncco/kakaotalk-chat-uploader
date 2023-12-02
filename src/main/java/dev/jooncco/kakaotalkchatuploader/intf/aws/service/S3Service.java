package dev.jooncco.kakaotalkchatuploader.intf.aws.service;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface S3Service {

    void uploadFile(File file);

    String generateAmazonS3ObjectKey(String openChatTitle, LocalDateTime dateTime, int sequence);
}
