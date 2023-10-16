package com.example.camelSnsRoute;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CamelSnsRouteApplication {


	public static void main(String[] args) {
        SpringApplication.run(CamelSnsRouteApplication.class, args);


//        CamelContext camelContext = new DefaultCamelContext();
//        camelContext.start();
//        String queue = "https://sqs.us-east-1.amazonaws.com/739067113029/real-queue";
//        Region region = Region.US_EAST_1;
//        SQSListener sqsListener = new SQSListener(queue,region);
//        sqsListener.setCamelContext(camelContext);
//        sqsListener.startListeningQueue();
	}

}
