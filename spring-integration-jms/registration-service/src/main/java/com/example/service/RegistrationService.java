package com.example.service;

import com.example.model.Registration;
import com.example.repository.RegistrationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
@Transactional
public class RegistrationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationService.class);

    @Autowired
    private RegistrationRepository registrationRepository;

    @ServiceActivator(inputChannel = "fromRabbitMQ")
    public void register(Message<Registration> message) {
        /*OffsetDateTime dateTime = (OffsetDateTime) message.getHeaders().get("dateTime");*/
        /*LOGGER.info("Registration received at: {} for ID {}", dateTime, message.getPayload().getId());*/
        LOGGER.info("Registration received for ID {}", message.getPayload().getId());
        Registration registration = message.getPayload();
        registrationRepository.save(registration);
        LOGGER.debug("Registration saved, ID: {}", registration.getId());
    }

}
