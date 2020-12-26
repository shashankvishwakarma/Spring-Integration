package com.example.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.amqp.dsl.Amqp;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Transformers;
import org.springframework.messaging.MessageChannel;

@Configuration
@EnableIntegration
@EnableRabbit
public class RegistrationConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationConfiguration.class);

    private static final String QUEUE_NAME = "input-message-queue";

    @Autowired
    private RabbitTemplate amqpTemplate;

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, false);
    }

    public MessageChannel registrationInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow amqpOutboundFlow() {
        return IntegrationFlows.from("registrationInputChannel")
                .transform(Transformers.toJson())
                .handle(Amqp.outboundAdapter(amqpTemplate)
                        .routingKey(queue().getName()))
                .get();
    }
}
