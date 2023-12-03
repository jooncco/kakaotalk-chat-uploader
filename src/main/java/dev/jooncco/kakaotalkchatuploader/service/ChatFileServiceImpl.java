package dev.jooncco.kakaotalkchatuploader.service;

import dev.jooncco.kakaotalkchatuploader.intf.aws.service.S3Service;
import dev.jooncco.kakaotalkchatuploader.utility.ParseUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static dev.jooncco.kakaotalkchatuploader.constants.CommonConstants.KOREA_TIMEZONE_DIFF;
import static dev.jooncco.kakaotalkchatuploader.constants.CommonConstants.OpenChat;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatFileServiceImpl implements ChatFileService {

    @Value("${kakaotalk.fileStore.path}")
    private String localStorePath;

    private final S3Service s3Service;

    @Override
    public void parseAndUploadFilesToAmazonS3(String sourceFilePath, long minutes) {
        File sourceFile= new File(sourceFilePath);
        try {
            BufferedReader reader= new BufferedReader(new FileReader(sourceFile));
            String firstLine= reader.readLine();
            String title= firstLine.substring(0, firstLine.indexOf(OpenChat.TITLE_SUFFIX))
                    .strip()
                    .replace('/', '∕')
                    .replace(' ', ' ');

            String line;
            do { // skip dump file meta data
                line= reader.readLine();
            } while (line != null && !isStartOfADay(line));

            while (line != null) {
                LocalDate date= ParseUtility.parseLocalDate(line);
                line= reader.readLine();

                String prevSpeaker= null;
                LocalTime prevTime= null;
                int seq= 0;
                while (line != null && !isStartOfADay(line)) {
                    if (ParseUtility.parseSpeaker(line) == null && prevSpeaker == null) {
                        // skip open chat notice
                        line= reader.readLine();
                        continue;
                    }

                    LocalTime time= ParseUtility.parseLocalTime(line);
                    if (time == null) time= prevTime;
                    LocalDateTime dateTime= LocalDateTime.of(date, time); // This is in UTC+9
                    dateTime= dateTime.minusHours(KOREA_TIMEZONE_DIFF);   // UTC+9 -> UTC
                    if (LocalDateTime.now().minusMinutes(minutes+1).isAfter(dateTime)) {
                        // skip old chats
                        line= reader.readLine();
                        continue;
                    }

                    String speaker= ParseUtility.parseSpeaker(line);
                    if (speaker == null) speaker= prevSpeaker;

                    String sentence= ParseUtility.parseSentence(line);

                    String parsedFileName= s3Service.generateAmazonS3ObjectKey(title, dateTime, seq);
                    File parsedFile= Paths.get(localStorePath, parsedFileName).toFile();
                    parsedFile.createNewFile();
                    BufferedWriter writer= new BufferedWriter(new FileWriter(parsedFile));
                    writer.write(speaker + ": " + sentence);
                    writer.close();

                    s3Service.uploadFile(parsedFile);
                    parsedFile.delete();

                    prevTime= time;
                    prevSpeaker= speaker;
                    ++seq;
                    line= reader.readLine();
                }
            }

            reader.close();
        } catch (IOException ex) {
            log.error("Error occurred: " + sourceFile.getPath(), ex);
        }
    }

    private boolean isStartOfADay(String line) {
        return ParseUtility.parseLocalDate(line) != null;
    }
}
