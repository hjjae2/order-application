package com.toy.modules.kafka;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;

@RequiredArgsConstructor
@EnableKafka
@Configuration
public class KafkaConsumerConfiguration {

    private final KafkaConsumerProperty.KafkaConsumerDefaultProperty kafkaConsumerDefaultProperty;
    private final KafkaConsumerProperty.KafkaConsumerOrderRequestProperty kafkaConsumerOrderRequestProperty;

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, Object> defaultKafkaListenerContainerFactory(ConsumerFactory<String, Object> defaultConsumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(defaultConsumerFactory);

        return factory;
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, Object> orderRequestKafkaListenerContainerFactory(ConsumerFactory<String, Object> orderRequestConsumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(orderRequestConsumerFactory);

        return factory;
    }

    @Bean
    public ConsumerFactory<String, Object> defaultConsumerFactory() {
        JsonDeserializer<Object> jsonDeserializer = new JsonDeserializer<>();
        jsonDeserializer.addTrustedPackages("*");

        return new DefaultKafkaConsumerFactory<>(
                new HashMap<>() {{
                    put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConsumerDefaultProperty.getBootstrapServer());
                    put(ConsumerConfig.GROUP_ID_CONFIG, kafkaConsumerDefaultProperty.getGroupId());
                    put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaConsumerDefaultProperty.getAutoOffsetReset());
                    put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
                    put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
                }},
                new StringDeserializer(),
                jsonDeserializer
        );
    }

    @Bean
    public ConsumerFactory<String, Object> orderRequestConsumerFactory() {
        JsonDeserializer<Object> jsonDeserializer = new JsonDeserializer<>();
        jsonDeserializer.addTrustedPackages("*");

        return new DefaultKafkaConsumerFactory<>(
                new HashMap<>(){{
                    put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConsumerOrderRequestProperty.getBootstrapServer());
                    put(ConsumerConfig.GROUP_ID_CONFIG, kafkaConsumerOrderRequestProperty.getGroupId());
                    put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaConsumerOrderRequestProperty.getAutoOffsetReset());
                    put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
                    put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
                }},
                new StringDeserializer(),
                jsonDeserializer
        );
    }
}
