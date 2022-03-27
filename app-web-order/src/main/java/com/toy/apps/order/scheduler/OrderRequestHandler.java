package com.toy.apps.order.scheduler;

import com.toy.modules.kafka.KafkaConsumerProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderRequestHandler {

    private final KafkaConsumerProperty.KafkaConsumerOrderRequestProperty kafkaConsumerOrderRequestProperty;

    @KafkaListener(
            topics = "toy-assignment-order-request-event",
            containerFactory = "orderRequestKafkaListenerContainerFactory"
    )
    public void listen(@Payload Object emsAlarm) throws MessagingException {
    }
}
