package com.example.gateway;

import com.example.model.Registration;
import org.springframework.integration.annotation.Gateway;
import org.springframework.messaging.Message;

@org.springframework.integration.annotation.MessagingGateway
public interface MessagingGateway {

    @Gateway(requestChannel = "registrationInputChannel")
    void send(Message<Registration> message);
}
