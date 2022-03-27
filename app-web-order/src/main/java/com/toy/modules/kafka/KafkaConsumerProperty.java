package com.toy.modules.kafka;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

public class KafkaConsumerProperty {

    @Setter
    @Getter
    @ConfigurationProperties(prefix = "application.kafka.consumer.default")
    @Configuration
    public static class KafkaConsumerDefaultProperty {
        private String bootstrapServer;
        private String groupId;
        private String autoOffsetReset;
    }

    @Setter
    @Getter
    @ConfigurationProperties(prefix = "application.kafka.consumer.order-request")
    @Configuration
    public static class KafkaConsumerOrderRequestProperty {
        private String bootstrapServer;
        private String groupId;
        private String autoOffsetReset;
    }
}
