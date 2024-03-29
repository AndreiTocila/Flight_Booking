package com.hcl.bookingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class BookingServiceApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(BookingServiceApplication.class, args);
    }

}
