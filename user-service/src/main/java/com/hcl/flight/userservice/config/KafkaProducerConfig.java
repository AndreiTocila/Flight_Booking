package com.hcl.flight.userservice.config;

import com.hcl.kafka.dto.AdminFeedback;
import com.hcl.kafka.dto.SeatReservationDTO;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {
  @Value("${kafka.bootstrap-servers}")
  private String bootstrapServers;

  @Value("${schema.registry.url}")
  private String schemaRegistryUrl;

  private Map<String, Object> getAvroProducerProperties() {
    Map<String, Object> props = new HashMap<>();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
    props.put("retries", 3);
    props.put("acks", "all");
    props.put("schema.registry.url", schemaRegistryUrl);

    return props;
  }

  @Bean
  public ProducerFactory<Long, AdminFeedback> adminFeedbackProducerFactory() {
    return new DefaultKafkaProducerFactory<>(getAvroProducerProperties());
  }

  @Bean
  public KafkaTemplate<Long, AdminFeedback> adminFeedbackKafkaTemplate() {
    return new KafkaTemplate<>(adminFeedbackProducerFactory());
  }

  @Bean
  public ProducerFactory<Long, SeatReservationDTO> seatReservationProducerFactory() {
    return new DefaultKafkaProducerFactory<>(getAvroProducerProperties());
  }

  @Bean
  public KafkaTemplate<Long, SeatReservationDTO> seatReservationKafkaTemplate() {
    return new KafkaTemplate<>(seatReservationProducerFactory());
  }
}
