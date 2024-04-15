package com.hcl.flight.userservice.controller;
import com.hcl.flight.userservice.dto.request.FlightsRequest;
import com.hcl.flight.userservice.dto.response.FlightResponse;
import com.hcl.flight.userservice.service.FlightsService;
import com.hcl.flight.userservice.service.KafkaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flights")
public class FlightsController {
  private final FlightsService flightsService;
  private final KafkaService kafkaService;

  public FlightsController(FlightsService flightsService, KafkaService kafkaService) {
    this.flightsService = flightsService;
    this.kafkaService = kafkaService;
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping()
  public void save(@RequestBody FlightsRequest flightsRequest) {
    flightsService.save(flightsRequest);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping("/{flightNumber}")
  public ResponseEntity<FlightResponse> getFlight(@PathVariable Long flightNumber) {
    kafkaService.sendFlightDetails(flightsService.getFlightByNumber(flightNumber));
    return ResponseEntity.ok(flightsService.getFlightByNumber(flightNumber));
  }
}
