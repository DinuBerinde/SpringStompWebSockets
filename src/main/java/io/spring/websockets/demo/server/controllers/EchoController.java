package io.spring.websockets.demo.server.controllers;

import io.spring.websockets.demo.models.HelloWorld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

/**
 * Example of a controller which handles a string message and an object helloWorld message.
 *
 * Clients should subscribe to /user/clientKey/echo/message in order
 * to receive messages and they should send messages to the destination /echo/message
 *
 * We use a clientKey because the message response is send to a specific client and
 * is not broadcast to all subscribed clients.
 */
@Controller
@MessageMapping("/echo")
public class EchoController {
    private final SimpMessagingTemplate simpMessagingTemplate;


    @Autowired
    public EchoController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/message")
    public void object(Principal principal, HelloWorld helloWorld) {
        simpMessagingTemplate.convertAndSendToUser(principal.getName(), "/echo/object", new HelloWorld("Returning from server " + helloWorld.message));
    }

}
