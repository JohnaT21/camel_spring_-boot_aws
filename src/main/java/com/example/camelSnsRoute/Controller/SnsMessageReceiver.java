package com.example.camelSnsRoute.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SnsMessageReceiver {

    public void receiveMessage(String message) {
        log.info("from the message snss message");
        log.info(message);
        System.out.println("Received SNS message: " + message);
        // Implement your custom logic here
    }
}
