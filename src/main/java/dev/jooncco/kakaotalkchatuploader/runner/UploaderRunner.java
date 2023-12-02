package dev.jooncco.kakaotalkchatuploader.runner;

import dev.jooncco.kakaotalkchatuploader.service.ChatFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UploaderRunner implements ApplicationRunner {

    @Value("${kakaotalk.fileStore.sourceFilePath}")
    private String sourceFilePath;

    @Value("${kakaotalk.fileStore.minutes}")
    private long minutes;

    private final ChatFileService chatFileService;

    @Override
    public void run(ApplicationArguments args) {
        chatFileService.parseAndUploadFilesToAmazonS3(sourceFilePath, minutes);
    }
}
