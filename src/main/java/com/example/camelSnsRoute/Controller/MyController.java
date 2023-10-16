package com.example.camelSnsRoute.Controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageResponse;

import java.util.List;

@RestController
@Slf4j
public class MyController {

    @Autowired
    private CamelContext camelContext;
    private ProducerTemplate producerTemplate;

    @PostMapping("/publish-message")
    public String publishMessage(@RequestBody String message) {
        try {
            // Get the ProducerTemplate from the CamelContext
             producerTemplate = camelContext.createProducerTemplate();

            // Send the request to the Camel route
            producerTemplate.sendBody("direct:publish", message);

            return "Message published to SNS topic successfully!";
        } catch (Exception e) {
            return "Error publishing message to SNS topic: " + e.getMessage();
        }
    }

    @GetMapping("/create-topic")
    public String CreateTopic() {
        try {
            // Get the ProducerTemplate from the CamelContext
            producerTemplate = camelContext.createProducerTemplate();

            // Send the request to the Camel route
            producerTemplate.sendBody("direct:createTopic", "topicName");

            return "a new topic is created successfully!";
        } catch (Exception e) {
            return "Error to create topic: " + e.getMessage();
        }
    }

    @GetMapping("/sendReceive")
    public void getRecieve(){
        log.info("on the get recieve");
        SqsClient sqsClient = SqsClient.create();

        // Specify the queue URL
        String queueUrl = "https://sqs.us-east-1.amazonaws.com/739067113029/real-queue";
        // Continuously poll the queue for messages
//        while (true) {
        ReceiveMessageRequest receiveRequest = ReceiveMessageRequest.builder()
            .queueUrl(queueUrl)
            .maxNumberOfMessages(10)
            .waitTimeSeconds(20)
            .build();

        // Receive messages from the queue
        ReceiveMessageResponse receiveResponse = sqsClient.receiveMessage(receiveRequest);
        List<Message> messages = receiveResponse.messages();

        log.info("inside the fjeoj");
        messages.forEach(message -> log.info(messages.toString()));

//        from("aws2-sqs://" + queueUrl)
//            .process(exchange -> {
//                Message message = exchange.getIn().getBody(Message.class);
//                // Process the message
//                log.info("Received message: " + message.body());
//            });

        // Process the received messages
//        for (
//    Message message : messages) {
//            // Process the message
//            System.out.println("Received message: " + message.body());
//
//            // Delete the message from the queue
//            DeleteMessageRequest deleteRequest = DeleteMessageRequest.builder()
//                .queueUrl(queueUrl)
//                .receiptHandle(message.receiptHandle())
//                .build();
//            sqsClient.deleteMessage(deleteRequest);
//        }
//    }
    }
}
