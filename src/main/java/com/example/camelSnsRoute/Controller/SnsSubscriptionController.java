package com.example.camelSnsRoute.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.SubscribeRequest;

@RestController
@RequestMapping("/subscription")
@Slf4j
public class SnsSubscriptionController {

    private final SnsClient snsClient;
    private final String queueArn;
    private final String topicArn;

    public SnsSubscriptionController(
        @Value("${aws.region}") String region,
        @Value("${aws.sns.topic-arn}") String topicArn,
        @Value("${aws.sqs.queue-arn}") String queueArn) {

        this.snsClient = SnsClient.builder()
            .region(Region.of(region))
            .credentialsProvider(DefaultCredentialsProvider.create())
            .build();

        this.topicArn = topicArn;
        this.queueArn = queueArn;
    }

    @PostMapping
    public ResponseEntity<String> subscribeQueueToTopic() {
        log.info(topicArn);
        SubscribeRequest subscribeRequest = SubscribeRequest.builder()
            .topicArn(topicArn)
            .protocol("sqs")
            .endpoint(queueArn)
            .build();

        snsClient.subscribe(subscribeRequest);

        return ResponseEntity.status(HttpStatus.OK).body("SQS queue subscribed to SNS topic.");
    }
}
