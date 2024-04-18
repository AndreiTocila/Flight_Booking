package com.hcl.bookingservice.service.impl;

import com.hcl.bookingservice.domain.Booking;
import com.hcl.bookingservice.dto.FlightDetailsDTO;
import com.hcl.bookingservice.service.KafkaService;
import com.hcl.kafka.dto.PaymentDTO;
import com.hcl.kafka.dto.SeatReservationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaServiceImpl implements KafkaService
{
    private final static Logger logger = LoggerFactory.getLogger(KafkaServiceImpl.class);

    private final KafkaTemplate<Long, SeatReservationDTO> adminKafkaTemplate;

    private final KafkaTemplate<Long, PaymentDTO> paymentKafkaTemplate;

    private final KafkaTemplate<String, String> notificationKafkaTemplate;

    public KafkaServiceImpl(KafkaTemplate<Long, SeatReservationDTO> adminKafkaTemplate, KafkaTemplate<Long, PaymentDTO> paymentKafkaTemplate, KafkaTemplate<String, String> notificationKafkaTemplate)
    {
        this.adminKafkaTemplate = adminKafkaTemplate;
        this.paymentKafkaTemplate = paymentKafkaTemplate;
        this.notificationKafkaTemplate = notificationKafkaTemplate;
    }

    @Override
    public void sendAddBookingMessages(Booking booking)
    {
        CompletableFuture.supplyAsync(() -> {
            FlightDetailsDTO flightDetails = booking.getFlight();

            sendAdminMessage(flightDetails.getId(), booking.getId(), booking.getNumberOfSeats());
//            sendPaymentMessage(flightDetails.getId(), booking.getId(), booking.getCardDetails().getIban(), flightDetails.getIban(), flightDetails.getPrice() * booking.getNumberOfSeats());
            sendNotificationMessage(booking.getId(), flightDetails, booking.getStatus());

            return null;
        }).whenComplete((result, exception) ->
        {
            if (exception == null)
            {
                logger.info("Booking: " + booking + " processed successfully");
            } else
            {
                logger.error(exception.getMessage());
            }
        });
    }

    @Override
    public void sendRejectedMessages(Booking booking, String message)
    {
        FlightDetailsDTO flightDetails = booking.getFlight();
        sendAdminMessage(flightDetails.getId(), booking.getId(), -booking.getNumberOfSeats());
        sendNotificationMessage(booking.getId(), flightDetails, "REJECTED", message);
    }

    @Override
    public void sendAfterAdminValidationMessages(Long flightId, Booking booking)
    {
        FlightDetailsDTO flightDetails = booking.getFlight();
        sendPaymentMessage(flightDetails.getId(), booking.getId(), booking.getCardDetails().getIban(), flightDetails.getIbanOperator(), flightDetails.getPrice() * booking.getNumberOfSeats());
    }

    @Override
    public void sendAfterPaymentValidationMessages(Booking booking, String status, Boolean validated)
    {
        if(!validated)
        {
            sendRejectedMessages(booking, "Payment failed!");
        }
        else
        {
            sendNotificationMessage(booking.getId(), booking.getFlight(), booking.getStatus());
        }
    }

    private void sendAdminMessage(Long flightId, String bookingId, Long numberOfSeats)
    {
        SeatReservationDTO reservationDTO = new SeatReservationDTO();
        reservationDTO.setBookingId(bookingId);
        reservationDTO.setNumberOfSeats(numberOfSeats);
        adminKafkaTemplate.send("seat_reservation", flightId, reservationDTO)
                .whenComplete((result, exception) ->
                {
                    if (exception == null)
                    {
                        logger.info("Send successfully to admin!");
                    } else
                    {
                        logger.error("Failed admin: " + exception.getMessage());
                    }
                });
    }

    private void sendPaymentMessage(Long flightId, String bookingId, String ibanClient, String ibanOperator, Double price)
    {
        PaymentDTO payment = new PaymentDTO();
        payment.setBookingId(bookingId);
        payment.setIbanClient(ibanClient);
        payment.setIbanOperator(ibanOperator);
        payment.setPrice(price);

        paymentKafkaTemplate.send("payment", flightId, payment)
                .whenComplete((result, exception) ->
                {
                    if (exception == null)
                    {
                        logger.info("Send successfully to payment!");
                    } else
                    {
                        logger.error("Failed payment: " + exception.getMessage());
                    }
                });
    }

    private void sendNotificationMessage(String bookingId, FlightDetailsDTO flightDetails, String status)
    {
        sendNotificationMessage(bookingId, flightDetails, status, "");
    }

    private void sendNotificationMessage(String bookingId, FlightDetailsDTO flightDetails, String status, String otherMentions)
    {
        notificationKafkaTemplate.send("notification", bookingId, buildNotificationMessage(flightDetails.getDeparture(), flightDetails.getArrival(), status, otherMentions))
                .whenComplete((result, exception) ->
                {
                    if (exception == null)
                    {
                        logger.info("Send successfully to notification!");
                    } else
                    {
                        logger.error("Failed notification: " + exception.getMessage());
                    }
                });
    }

    private String buildNotificationMessage(String departure, String arrival, String status, String otherMentions)
    {
        StringBuilder sb = new StringBuilder();
        sb
                .append("Flight from ")
                .append(departure)
                .append(" to ")
                .append(arrival)
                .append(". ")
                .append("STATUS: ")
                .append(status)
                .append(". ")
                .append(otherMentions);

        return sb.toString();
    }
}
