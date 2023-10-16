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


@Component
@Slf4j
public class S3Route extends RouteBuilder {



    @Override
    public void configure() throws Exception {
        String s3Endpoint = "aws2-s3://arn:aws:s3:::johnatalbucket?accessKey=arn:aws:s3:::johnatalbucket&secretKey=Eu12d6N3clR06Cpifc4EPuc91W+oAUi2oGx6GXwJ&region=us-east-1";

        log.info("the file upload");
        from("file:C:\\Users\\jenye\\project\\SpringBoot\\camelSnsRoute\\src\\main\\resources\\data")  // Replace with the source directory path
            .log("the fiel reading:${body}")
            .process(exchange -> {
                log.info(exchange.getMessage().toString());
            })
            .setHeader("CamelAwsS3Key", simple("${file:onlyname}"))
            .to(s3Endpoint)
            .log("File uploaded to S3");
    }
}
