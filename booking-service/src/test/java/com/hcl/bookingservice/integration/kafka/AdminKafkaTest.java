package com.hcl.bookingservice.integration.kafka;

import com.hcl.bookingservice.integration.config.KafkaIntegrationTestConfig;
import com.hcl.kafka.dto.SeatReservationDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class AdminKafkaTest
{
    private static final Network NETWORK = Network.newNetwork();

    @Container
    private static final KafkaContainer KAFKA_CONTAINER =
            new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.5.2"))
                    .withNetwork(NETWORK);

    @Container
    private static final GenericContainer<?> SCHEMA_REGISTRY =
            new GenericContainer<>(DockerImageName.parse("confluentinc/cp-schema-registry:7.5.2"))
                    .withNetwork(NETWORK)
                    .withExposedPorts(8081)
                    .withEnv("SCHEMA_REGISTRY_HOST_NAME", "schema-registry")
                    .withEnv("SCHEMA_REGISTRY_LISTENERS", "http://0.0.0.0:8081")
                    .withEnv("SCHEMA_REGISTRY_KAFKASTORE_BOOTSTRAP_SERVERS",
                            "PLAINTEXT://" + KAFKA_CONTAINER.getNetworkAliases().get(0) + ":9092")
                    .waitingFor(Wait.forHttp("/subjects").forStatusCode(200));

//    @Container
//    public static MongoDBContainer MONGODB_CONTAINER = new MongoDBContainer("mongo:latest");

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry)
    {
        registry.add("kafka.bootstrap-servers", KAFKA_CONTAINER::getBootstrapServers);
        registry.add("schema.registry.url", () -> "http://" + SCHEMA_REGISTRY.getHost() + ":" + SCHEMA_REGISTRY.getFirstMappedPort());
//        registry.add("spring.data.mongodb.uri", () -> "mongodb://" + MONGODB_CONTAINER.getHost() + ":" + MONGODB_CONTAINER.getFirstMappedPort() + "/Bookings");
    }

    @Autowired
    private KafkaTemplate<Long, SeatReservationDTO> adminKafkaTemplate;

    @Test
    public void testKafkaIsUp() throws ExecutionException, InterruptedException
    {
        SeatReservationDTO reservationDTO = new SeatReservationDTO();
        reservationDTO.setId("bookingId");
        reservationDTO.setNumberOfSeats(5);

        CountDownLatch latch = new CountDownLatch(1);

        adminKafkaTemplate.send("seat_reservation", 5L, reservationDTO)
                .whenComplete((result, exception) ->
                {
                    if (exception == null)
                    {
                        latch.countDown();
                    }
                });

        assertTrue(latch.await(10, TimeUnit.SECONDS));
    }
}
