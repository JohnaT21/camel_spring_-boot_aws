package com.example.camelSnsRoute.Controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.aws2.sns.Sns2Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;

@Component
@Slf4j
public class ApacheCamelSnsRouter extends RouteBuilder {

    @Autowired
    private final CamelContext camelContext;

    public ApacheCamelSnsRouter(CamelContext camelContext) {
        this.camelContext = camelContext;
    }


    @Override
    public void configure() throws Exception {
        log.info("inside the route builder");

        try {

            Sns2Configuration sns2Configuration = new Sns2Configuration();
            sns2Configuration.setSubscribeSNStoSQS(true);

//
//            from("direct:publish")
//                .to("aws2-sns://arn:aws:sns:us-east-1:739067113029:real-sns?accessKey=AKIA2YE6VFZCRZGT7ENJ&secretKey=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&region=us-east-1")
//                .log("the message sent succesfully");
            from("direct:publish")
                .to("aws2-sns://arn:aws:sns:us-east-1:739067113029:real-sns?accessKey=AKIA2YE6VFZCRZGT7ENJ&secretKey=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&region=us-east-1")

                .log("the message sent succesfully")
            ;


        }catch (Exception e){
            log.info(e.getMessage());
        }

    }


}
