package dev.jooncco.kakaotalkchatuploader.service;

import static dev.jooncco.kakaotalkchatuploader.constants.CommonConstants.*;
import static dev.jooncco.kakaotalkchatuploader.utility.DateTimeUtility.getFormattedString;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import dev.jooncco.kakaotalkchatuploader.intf.aws.service.S3Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatFileServiceImpl implements ChatFileService {

    private final S3Service s3Service;

    // /** 업로드가 필요한 로그파일들을 압축 후에 S3 bucket 으로 업로드 해주는 서비스 메소드. */
    // @Override
    // public void archiveAndUploadToAmazonS3(LocalDateTime now) {
    //     // File sourceDirectory = getHourSourceDirectory(now);
    //     log.info(
    //             "Checking if any log files need to be uploaded. Path: {}",
    //             sourceDirectory.getAbsolutePath());
    //     if (ObjectUtils.isEmpty(sourceDirectory.listFiles())) {
    //         log.info("Empty source directory. Aborting.");
    //         return;
    //     }

    //     List<File> targetLogFiles = new ArrayList<>();
    //     Set<String> alreadyUploadedFileNames = new HashSet<>();
    //     for (File hostDirectory : sourceDirectory.listFiles()) {
    //         if (!hostDirectory.isDirectory() || hostDirectory.listFiles() == null) {
    //             continue;
    //         }
    //         for (File logFile : hostDirectory.listFiles()) {
    //             String name = logFile.getName();
    //             // String extensionSlug = getFileExtension(logFile);
    //             if (GZIP.getSlug().equals(extensionSlug)) {
    //                 alreadyUploadedFileNames.add(name.substring(0, name.lastIndexOf(".")));
    //                 continue;
    //             }
    //             if (!name.contains(DEVICE_STAT_LOG_FILE_SUBSTRING)) {
    //                 continue;
    //             }
    //             if (!LOG.getSlug().equalsIgnoreCase(extensionSlug)) {
    //                 continue;
    //             }

    //             targetLogFiles.add(logFile);
    //         }
    //     }
    //     List<File> gzippedFiles = new ArrayList<>();
    //     for (File file : targetLogFiles) {
    //         if (alreadyUploadedFileNames.contains(getFileNameWithoutExtension(file))) {
    //             continue;
    //         }

    //         String gzippedFileName = file.getName() + "." + GZIP.getSlug();
    //         String gzippedFilePath =
    //                 Paths.get(file.getParentFile().getPath(), gzippedFileName).toString();
    //         compressGzipFile(file.getPath(), gzippedFilePath);
    //         gzippedFiles.add(new File(gzippedFilePath));
    //     }
    //     if (gzippedFiles.isEmpty()) {
    //         log.info("Already up to date. Skip uploading.");
    //         return;
    //     }

    //     s3Service.uploadFiles(gzippedFiles, true);
    // }

    // /** TTL이 지난 로그들을 EFS 볼륨에서 삭제 해주는 서비스 메소드. */
    // @Override
    // public void deleteOldFiles(LocalDateTime now) {
    //     File oldMonthDirectory = getMonthSourceDirectory(now.minusMonths(DELETE_MONTH_BEFORE_NOW));
    //     try {
    //         FileUtils.deleteDirectory(oldMonthDirectory);
    //     } catch (IOException ex) {
    //         log.error("Delete directory failed. Directory path: {}", oldMonthDirectory.getPath());
    //     }

    //     for (int days = logFileTtl + 1; days <= logFileTtl + DELETE_DAYS_BEFORE_TTL; ++days) {
    //         File oldDayDirectory = getDaySourceDirectory(now.minusDays(days));
    //         try {
    //             FileUtils.deleteDirectory(oldDayDirectory);
    //         } catch (IOException ex) {
    //             log.error("Delete directory failed. Directory path: {}", oldDayDirectory.getPath());
    //         }
    //     }
    // }
}
