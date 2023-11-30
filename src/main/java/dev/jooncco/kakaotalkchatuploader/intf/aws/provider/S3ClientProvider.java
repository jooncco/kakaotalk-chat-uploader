package dev.jooncco.kakaotalkchatuploader.intf.aws.provider;

import com.amazonaws.services.s3.AmazonS3Client;

public interface S3ClientProvider {

    AmazonS3Client getClient();
}
