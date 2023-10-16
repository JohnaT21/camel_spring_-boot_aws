//package com.example.camelSnsRoute.Controller;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.camel.CamelContext;
//import org.apache.camel.Exchange;
//import org.apache.camel.Processor;
//import org.apache.camel.Route;
//import org.apache.camel.builder.RouteBuilder;
//import org.apache.camel.component.aws2.sns.Sns2Component;
//import org.apache.camel.component.aws2.sqs.Sqs2Component;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
//import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
//import software.amazon.awssdk.regions.Region;
//import software.amazon.awssdk.services.sns.SnsClient;
//import software.amazon.awssdk.services.sqs.SqsClient;
//
//import java.util.UUID;
//
//@Component
//@Slf4j
//public class SnsRoute extends RouteBuilder {
//
//    @Autowired
//    private CamelContext camelContext;
//    @Override
//    public void configure() throws Exception {
//       log.info("from sns route");
//        AwsBasicCredentials awsCredentials = AwsBasicCredentials.create("AKIA2YE6VFZC7NZGBNP7", "Eu12d6N3clR06Cpifc4EPuc91W+oAUi2oGx6GXwJ");
//        SnsClient snsClient = SnsClient.builder()
//            .region(Region.US_EAST_1)
//            .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create("AKIA2YE6VFZC7NZGBNP7","Eu12d6N3clR06Cpifc4EPuc91W+oAUi2oGx6GXwJ")))
//            .build();;
//        Sns2Component sns2Component = new Sns2Component();
//        sns2Component.getConfiguration().setAmazonSNSClient(snsClient);
//        sns2Component.getConfiguration().setAccessKey(awsCredentials.accessKeyId());
//        sns2Component.getConfiguration().setSecretKey(awsCredentials.secretAccessKey());
//        sns2Component.getConfiguration().setRegion("us-east-1");
//        sns2Component.getConfiguration().setTopicName("real-sns");
//        camelContext.addComponent("aws2-sns", sns2Component);
//
//        from("direct:snsRoute")
//            .log("from sns file:${body}")
//            .convertBodyTo(String.class)
//            .log("form the sns file: ${body}")
//            .process(new RandomStringProcessor())
//            .to("direct:uploadToS3");
//    }
//    private static class RandomStringProcessor implements Processor {
//        @Override
//        public void process(Exchange exchange) throws Exception {
//            String randomString = UUID.randomUUID().toString().substring(0, 10);
//            String snsSubject = exchange.getMessage().getHeader("CamelAwsSnsSubject", String.class);
//            if (snsSubject == null) {
//                snsSubject = "default";
//            }
//            String fileName = snsSubject + "-" + randomString + ".json";
//            exchange.getMessage().setHeader("fileName", fileName);
//        }
//    }
//}
