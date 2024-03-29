package com.hcl.bookingservice.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig
{
    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    private Map<String, Object> getProducerProperties(Class<?> keySerializerClass, Class<?> valueSerializerClass)
    {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializerClass);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializerClass);

        return props;
    }
    @Bean
    public ProducerFactory<Long, Integer> adminProducerFactory()
    {
        return new DefaultKafkaProducerFactory<>(getProducerProperties(LongSerializer.class, IntegerSerializer.class));
    }

    @Bean
    public ProducerFactory<String, String> notificationProducerFactory()
    {
        return new DefaultKafkaProducerFactory<>(getProducerProperties(StringSerializer.class, StringSerializer.class));
    }

    @Bean
    public KafkaTemplate<Long, Integer> adminKafkaTemplate()
    {
        return new KafkaTemplate<>(adminProducerFactory());
    }

    @Bean
    public KafkaTemplate<String, String> notificationKafkaTemplate()
    {
        return new KafkaTemplate<>(notificationProducerFactory());
    }
}
