package com.example.camelSnsRoute.Controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SnsMessageController {

    @Autowired
    private CamelContext camelContext;


//    private final SnsMessageReceiver messageReceiver;
//    @Autowired
//    public SnsMessageController(SnsMessageReceiver messageReceiver) {
//        this.messageReceiver = messageReceiver;
//    }

    @PostMapping("/sns-endpoint")
    @ResponseStatus(HttpStatus.OK)
    public void receiveSnsMessage(@RequestBody String message) {
        log.info("the message body");
        log.info(message);
        log.info(message.getClass().toGenericString());
//        messageReceiver.receiveMessage(message);
        // Get the ProducerTemplate from the CamelContext
        ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
        producerTemplate.sendBody("direct:snsRoute", message);
    }
}
