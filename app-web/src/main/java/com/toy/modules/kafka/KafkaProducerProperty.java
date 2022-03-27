package com.toy.modules.kafka;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

public class KafkaProducerProperty {

    @Setter
    @Getter
    @ConfigurationProperties(prefix = "application.kafka.producer.default")
    @Configuration
    public static class KafkaProducerDefaultProperty {
        private String bootstrapServer;
        private Integer lingerMs;
    }

    @Setter
    @Getter
    @ConfigurationProperties(prefix = "application.kafka.producer.order-request")
    @Configuration
    public static class KafkaProducerEmsProperty {
        private String bootstrapServer;
        private String topic;
        private Integer lingerMs;
    }
}
