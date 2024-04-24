package com.hcl.bookingservice.config;

import com.hcl.kafka.dto.AdminFeedback;
import com.hcl.kafka.dto.NotificationDTO;
import com.hcl.kafka.dto.PaymentDTO;
import com.hcl.kafka.dto.SeatReservationDTO;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig
{
    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${schema.registry.url}")
    private String schemaRegistryUrl;

    private Map<String, Object> getProducerProperties(Class<?> keySerializerClass, Class<?> valueSerializerClass)
    {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializerClass);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializerClass);
        props.put("retries", 3);
        props.put("acks", "all");

        return props;
    }

    private Map<String, Object> getAvroProducerProperties(Class<?> keySerializerClass)
    {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializerClass);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        props.put("retries", 3);
        props.put("acks", "all");
        props.put("schema.registry.url", schemaRegistryUrl);

        return props;
    }
    @Bean
    public ProducerFactory<Long, SeatReservationDTO> adminProducerFactory()
    {
        return new DefaultKafkaProducerFactory<>(getAvroProducerProperties(LongSerializer.class));
    }

    @Bean
    public KafkaTemplate<Long, SeatReservationDTO> adminKafkaTemplate()
    {
        return new KafkaTemplate<>(adminProducerFactory());
    }

    @Bean
    public ProducerFactory<Long, PaymentDTO> paymenetProducerFactory()
    {
        return new DefaultKafkaProducerFactory<>(getAvroProducerProperties(LongSerializer.class));
    }

    @Bean
    public KafkaTemplate<Long, PaymentDTO> paymentKafkaTemplate()
    {
        return new KafkaTemplate<>(paymenetProducerFactory());
    }

    @Bean
    public ProducerFactory<String, NotificationDTO> notificationProducerFactory()
    {
        return new DefaultKafkaProducerFactory<>(getAvroProducerProperties(StringSerializer.class));
    }

    @Bean
    public KafkaTemplate<String, NotificationDTO> notificationKafkaTemplate()
    {
        return new KafkaTemplate<>(notificationProducerFactory());
    }

    @Bean
    public ProducerFactory<String, String> dtoProducerFactory()
    {
        return new DefaultKafkaProducerFactory<>(getProducerProperties(StringSerializer.class,StringSerializer.class));
    }

    @Bean
    public KafkaTemplate<String, String> dtoKafkaTemplate()
    {
        return new KafkaTemplate<>(dtoProducerFactory());
    }
}
