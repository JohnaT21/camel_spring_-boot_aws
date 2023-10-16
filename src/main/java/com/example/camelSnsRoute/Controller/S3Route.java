package com.example.camelSnsRoute.Controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.aws2.s3.AWS2S3Component;
import org.apache.camel.component.aws2.sqs.Sqs2Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.sqs.SqsClient;

import java.util.UUID;


@Component
@Slf4j
public class S3Route extends RouteBuilder {

        @Autowired
        private CamelContext camelContext;

    @Override
    public void configure() throws Exception {
        String s3Endpoint = "aws2-s3://arn:aws:s3:::johnatalbucket?accessKey=AKIA2YE6VFZC7NZGBNP7&secretKey=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&region=us-east-1";

        log.info("the file upload");
        AwsBasicCredentials awsCredentials = AwsBasicCredentials.create("AKIA2YE6VFZC7NZGBNP7", "Eu12d6N3clR06Cpifc4EPuc91W+oAUi2oGx6GXwJ");
        S3Client s3Client = S3Client.builder()
            .region(Region.US_EAST_1)
            .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create("AKIA2YE6VFZC7NZGBNP7","Eu12d6N3clR06Cpifc4EPuc91W+oAUi2oGx6GXwJ")))
            .build();;
        AWS2S3Component aws2S3Component = new AWS2S3Component();
        aws2S3Component.getConfiguration().setAmazonS3Client(s3Client);
        aws2S3Component.getConfiguration().setAccessKey(awsCredentials.accessKeyId());
        aws2S3Component.getConfiguration().setSecretKey(awsCredentials.secretAccessKey());
        aws2S3Component.getConfiguration().setRegion("us-east-1");
        aws2S3Component.getConfiguration().setBucketName("johnatalbucket");
        camelContext.addComponent("aws2-s3", aws2S3Component);

        from("direct:snsRoute")
            .log("from sns file:${body}")
            .convertBodyTo(String.class)
            .log("form the sns file: ${body}")
            .to("direct:uploadToS3");

        from("direct:uploadToS3")
            .log("the fiel Http reading:${body}")
            .setHeader("CamelAwsS3Key", header("fileName"))  // Replace with the source directory path
            .process(exchange -> {
                log.info(exchange.getMessage().toString());
            })
            .setHeader("CamelAwsS3Key", constant("uploadFile.json"))
            .to(s3Endpoint)
            .log("Fttp File uploaded to S3");
    }
    public String randomString(int length) {
        return UUID.randomUUID().toString().substring(0, length);
    }
}
