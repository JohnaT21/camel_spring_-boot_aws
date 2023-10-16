    //package com.example.camelSnsRoute;
    //
    //import lombok.extern.slf4j.Slf4j;
    //import org.apache.camel.CamelContext;
    //import org.apache.camel.ProducerTemplate;
    //import org.apache.camel.builder.RouteBuilder;
    //import org.apache.camel.component.aws2.sqs.Sqs2Component;
    //import org.apache.camel.impl.DefaultCamelContext;
    //import org.springframework.beans.factory.annotation.Autowired;
    //import org.springframework.beans.factory.annotation.Qualifier;
    //import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
    //import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
    //import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
    //import software.amazon.awssdk.regions.Region;
    //import software.amazon.awssdk.services.sqs.SqsClient;
    //import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
    //import software.amazon.awssdk.services.sqs.model.Message;
    //import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
    //
    //import java.time.LocalDateTime;
    //import java.time.format.DateTimeFormatter;
    //import java.util.List;
    //
    //
    //@Slf4j
    //public class SQSListener {
    //
    //
    //@Autowired
    //    private CamelContext camelContext;
    //    private final String queueUrl;
    //    private final Region region;
    //    private final SqsClient sqsClient;
    //
    //    public SQSListener(String queueUrl, Region region) {
    //        this.queueUrl = queueUrl;
    //        this.region = region;
    //        this.sqsClient = SqsClient.builder()
    //            .region(region)
    //            .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create("AKIA2YE6VFZC7NZGBNP7","Eu12d6N3clR06Cpifc4EPuc91W+oAUi2oGx6GXwJ")))
    //            .build();
    //    }
    //
    //    public void setCamelContext(CamelContext camelContext) {
    //        this.camelContext = camelContext;
    //    }
    //
    //
    //    public void startListeningQueue() {
    //        log.info("from the listener");
    //        ReceiveMessageRequest receiveRequest = ReceiveMessageRequest.builder()
    //            .queueUrl(queueUrl)
    //            .maxNumberOfMessages(1)
    //            .build();
    //
    //        while (true) {
    //            List<Message> messages = sqsClient.receiveMessage(receiveRequest).messages();
    //
    //            for (Message message : messages) {
    //                // Process the message received from the SQS queue
    //                System.out.println("Received message: " + message.body());
    //
    //                String body = message.body();
    //
    //                // Send the message body to a Camel endpoint for further processing
    //                sendToEndpoint(body);
    //
    //
    //                log.info("before delete");
    //                // Delete the message from the queue
    //                DeleteMessageRequest deleteRequest = DeleteMessageRequest.builder()
    //                    .queueUrl(queueUrl)
    //                    .receiptHandle(message.receiptHandle())
    //                    .build();
    //                log.info("after delet");
    //
    //                sqsClient.deleteMessage(deleteRequest);
    //            }
    //        }
    //    }
    //
    //    private void sendToEndpoint(String messageBody) {
    //        AwsBasicCredentials awsCredentials = AwsBasicCredentials.create("AKIA2YE6VFZC7NZGBNP7", "Eu12d6N3clR06Cpifc4EPuc91W+oAUi2oGx6GXwJ");
    //
    //        // Configure the SQS component
    //        Sqs2Component sqsComponent = new Sqs2Component();
    //        sqsComponent.getConfiguration().setAmazonSQSClient(sqsClient);
    //        sqsComponent.getConfiguration().setAccessKey(awsCredentials.accessKeyId());
    //        sqsComponent.getConfiguration().setSecretKey(awsCredentials.secretAccessKey());
    //        sqsComponent.getConfiguration().setRegion("us-east-1");
    //        sqsComponent.getConfiguration().setQueueName("real-queue");
    //        camelContext.addComponent("aws2-sqs", sqsComponent);
    //        LocalDateTime currentTime = LocalDateTime.now();
    //        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    //        String formattedTime = currentTime.format(formatter);
    //        try {
    //
    //            camelContext.addRoutes(new RouteBuilder() {
    //                @Override
    //                public void configure() {
    //                    // Define the route logic
    //                    log.info("from the camel inside the listener:{}", formattedTime);
    //                    from("aws2-sqs://arn:aws:sqs:us-east-1:739067113029:real-queue")
    //                        .log("Body: ${body}")
    //                     .to("file:src/main/resources/data?fileName=message.json");
    //                }
    //            });
    //            camelContext.start();
    //
    //
    //            log.info("context start:{}",formattedTime);
    //            // Keep the application running
    //            Thread.sleep(Long.MAX_VALUE);
    //            log.info("context thread sleep:{}",formattedTime);
    //
    ////            while (camelContext.getEndpoints().isEmpty()) {
    ////                log.info("inside the while");
    ////                Thread.sleep(60000); // Wait for 1 second before checking again
    ////                log.info("insid out");
    ////            }
    ////            log.info("after while");
    ////            // Keep the application running
    ////            Thread.sleep(60000);
    //
    //            // Stop the CamelContext
    //            camelContext.stop();
    //            log.info("context stopped");
    //        } catch (Exception e) {
    //            // Handle any exceptions
    //            e.printStackTrace();
    //        }
    //
    //    }
    //}
