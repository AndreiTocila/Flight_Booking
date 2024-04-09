package com.hcl.bookingservice.cronjob;

import com.hcl.bookingservice.domain.Booking;
import com.hcl.bookingservice.repository.BookingRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;

@Component
public class RejectedScheduler
{
    private final BookingRepository bookingRepository;

    public RejectedScheduler(BookingRepository bookingRepository)
    {
        this.bookingRepository = bookingRepository;
    }

    @Scheduled(fixedRate = 300_000)
    public void setStatusToRejected()
    {
        System.out.println("Cheching RejectedScheduler");
        bookingRepository.findByExpirationDateLessThanAndStatus(LocalDateTime.now().minusMinutes(15L), "RESERVED")
                .flatMap(booking ->
                {
                    booking.setStatus("REJECTED");
                    return bookingRepository.save(booking);
                })
                .subscribe();

    }
}
