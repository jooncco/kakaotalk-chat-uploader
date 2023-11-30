package dev.jooncco.kakaotalkchatuploader.intf.aws.provider;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Profile("!local")
@Component
public class NonLocalS3ClientProvider implements S3ClientProvider {

    @Value("${aws.s3.region}")
    private String s3Region;

    private AmazonS3Client amazonS3Client;

    @Override
    public AmazonS3Client getClient() {
        if (amazonS3Client != null) {
            return amazonS3Client;
        }

        log.debug("Creating non-default amazon s3 client ...");
        return amazonS3Client =
                (AmazonS3Client)
                        AmazonS3ClientBuilder.standard()
                                .withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
                                .withRegion(s3Region)
                                .build();
    }
}
