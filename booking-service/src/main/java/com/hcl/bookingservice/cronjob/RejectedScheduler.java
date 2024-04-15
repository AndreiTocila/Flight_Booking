package com.hcl.bookingservice.cronjob;

import com.hcl.bookingservice.repository.BookingRepository;
import com.hcl.bookingservice.service.KafkaService;
import com.hcl.kafka.dto.SeatReservationDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RejectedScheduler
{
    private final BookingRepository bookingRepository;

    private final KafkaService kafkaService;

    public RejectedScheduler(BookingRepository bookingRepository, KafkaService kafkaService)
    {
        this.bookingRepository = bookingRepository;
        this.kafkaService = kafkaService;
    }

    @Scheduled(fixedRate = 300_000)
    public void setStatusToRejected()
    {
        System.out.println("Cheching RejectedScheduler");
        bookingRepository.findByExpirationDateLessThanAndStatus(LocalDateTime.now().minusMinutes(15L), "RESERVED")
                .flatMap(booking ->
                {
                    kafkaService.sendRejectedMessages(booking, "Time expired!");
                    booking.setStatus("REJECTED");
                    return bookingRepository.save(booking);
                })
                .subscribe();

    }
}
