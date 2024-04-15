package com.hcl.flight.userservice.service;

import com.hcl.flight.userservice.dto.response.FlightResponse;
import com.hcl.flight.userservice.model.FlightOperator;
import com.hcl.flight.userservice.repository.FlightRepository;
import com.hcl.kafka.dto.AdminFeedback;
import com.hcl.kafka.dto.SeatReservationDTO;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {
  private final KafkaTemplate<Long, SeatReservationDTO> kafkaTemplate;
  private final KafkaTemplate<Long, AdminFeedback> adminFeedbackKafkaTemplate;
  private final FlightRepository flightRepository;

  public KafkaService(
      KafkaTemplate<Long, SeatReservationDTO> kafkaTemplate,
      KafkaTemplate<Long, AdminFeedback> adminFeedbackKafkaTemplate,
      FlightRepository flightRepository) {
    this.kafkaTemplate = kafkaTemplate;
    this.adminFeedbackKafkaTemplate = adminFeedbackKafkaTemplate;
    this.flightRepository = flightRepository;
  }

  public void sendFlightDetails(FlightResponse flightResponse) {
    SeatReservationDTO reservationDTO = new SeatReservationDTO();
    reservationDTO.setBookingId(Long.toString(flightResponse.getFlightResponseData().getId()));
    reservationDTO.setNumberOfSeats(-2L);
    kafkaTemplate.send("seat_reservation", 20L, reservationDTO);
  }

  public void sendAdminFeedBack(ConsumerRecord<Long, GenericRecord> consumerRecord) {
    AdminFeedback adminFeedback = new AdminFeedback();
    adminFeedback.setBookingId((String) consumerRecord.value().get("bookingId"));
    boolean status =
        statusValidation(consumerRecord.key(), (Long) consumerRecord.value().get("numberOfSeats"));
    adminFeedback.setStatusValidation(status);
    adminFeedbackKafkaTemplate.send("admin_feedback", consumerRecord.key(), adminFeedback);
  }

  private boolean statusValidation(Long flightNumber, Long bookedSeats) {
    boolean status = false;
    FlightOperator flightOperator = flightRepository.findByFlightNumber(flightNumber).get();
    if (bookedSeats <= flightOperator.getNumberOfSeats()) {
      if (bookedSeats > 0) {
        flightOperator.setNumberOfSeats(flightOperator.getNumberOfSeats() - bookedSeats);
        status = true;
      } else if (bookedSeats < 0) {
        flightOperator.setNumberOfSeats(flightOperator.getNumberOfSeats() + Math.abs(bookedSeats));
      }
      flightRepository.save(flightOperator);
    }
    return status;
  }
}
