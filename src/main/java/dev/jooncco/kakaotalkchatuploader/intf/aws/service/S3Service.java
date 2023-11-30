package dev.jooncco.kakaotalkchatuploader.intf.aws.service;

import java.io.File;
import java.util.List;

public interface S3Service {

    void uploadFiles(List<File> files, boolean deleteOnFail);
}
