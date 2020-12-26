package com.example.controllers;

import com.example.gateway.MessagingGateway;
import com.example.model.Registration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class RegistrationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private MessagingGateway gateway;

    @GetMapping
    public String index(@ModelAttribute("registration") Registration registration) {
        return "index";
    }

    @PostMapping
    public String submit(@ModelAttribute("registration") @Valid Registration registration, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            LOGGER.warn("Validation failed: {}", bindingResult);
            return "index";
        }

        String registrationID = UUID.randomUUID().toString();
        registration.setId(registrationID);
        Message<Registration> message = MessageBuilder.withPayload(registration)
                .setHeader("dateTime", OffsetDateTime.now())
                .build();

        gateway.send(message);
        /*String json = new Gson().toJson(msg);*/
        LOGGER.info("Message sent to registration request channel {}", message);
        return "success";
    }
}
