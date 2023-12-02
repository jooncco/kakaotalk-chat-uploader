package dev.jooncco.kakaotalkchatuploader.service;

public interface ChatFileService {

    void parseAndUploadFilesToAmazonS3(String sourceFilePath, long minutes);
}
