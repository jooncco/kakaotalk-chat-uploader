package dev.jooncco.kakaotalkchatuploader.intf.aws.provider;

import static com.amazonaws.regions.Regions.AP_NORTHEAST_2;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Profile("local")
@Component
public class LocalS3ClientProvider implements S3ClientProvider {

    private AmazonS3Client amazonS3Client;

    @Override
    public AmazonS3Client getClient() {
        if (amazonS3Client != null) {
            return amazonS3Client;
        }

        log.debug("Creating default amazon s3 client ...");
        return amazonS3Client =
                (AmazonS3Client)
                        AmazonS3ClientBuilder.standard()
                                .withEndpointConfiguration(
                                        new AwsClientBuilder.EndpointConfiguration(
                                                "http://localhost:4566/", AP_NORTHEAST_2.getName()))
                                .withCredentials(
                                        new AWSStaticCredentialsProvider(
                                                new BasicSessionCredentials(
                                                        "dummyKey", "dummySecret", "dummyToken")))
                                .withPathStyleAccessEnabled(true)
                                .build();
    }
}
