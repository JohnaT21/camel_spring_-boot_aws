package com.example.camelSnsRoute.Controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.aws2.sqs.Sqs2Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

@Component
@Slf4j
public class SQSSNSRoute extends RouteBuilder {
    @Autowired
    private CamelContext camelContext;

    @Override
    public void configure() throws Exception {
        AwsBasicCredentials awsCredentials = AwsBasicCredentials.create("AKIA2YE6VFZC7NZGBNP7", "Eu12d6N3clR06Cpifc4EPuc91W+oAUi2oGx6GXwJ");
        SqsClient sqsClient = SqsClient.builder()
            .region(Region.US_EAST_1)
            .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create("AKIA2YE6VFZC7NZGBNP7","Eu12d6N3clR06Cpifc4EPuc91W+oAUi2oGx6GXwJ")))
            .build();;
        Sqs2Component sqsComponent = new Sqs2Component();
        sqsComponent.getConfiguration().setAmazonSQSClient(sqsClient);
        sqsComponent.getConfiguration().setAccessKey(awsCredentials.accessKeyId());
        sqsComponent.getConfiguration().setSecretKey(awsCredentials.secretAccessKey());
        sqsComponent.getConfiguration().setRegion("us-east-1");
        sqsComponent.getConfiguration().setQueueName("real-queue");
        camelContext.addComponent("aws2-sqs", sqsComponent);

        log.info("from the camel inside route the listener:{}");
        from("aws2-sqs://arn:aws:sqs:us-east-1:739067113029:real-queue")
            .log("Body: ${body}")
            .to("direct:sqsRoute")
         ;
    }
}
