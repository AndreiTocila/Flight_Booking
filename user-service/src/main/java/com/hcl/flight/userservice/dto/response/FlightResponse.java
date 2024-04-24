package com.hcl.flight.userservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hcl.flight.userservice.dto.response.data.FlightResponseData;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlightResponse {
    @JsonProperty("data")
    private FlightResponseData flightResponseData;

    public FlightResponse(FlightResponseData flightResponseData) {
        this.flightResponseData = flightResponseData;
    }

    public FlightResponse() {
    }

    public FlightResponseData getFlightResponseData() {
        return flightResponseData;
    }

    public void setFlightResponseData(FlightResponseData flightResponseData) {
        this.flightResponseData = flightResponseData;
    }
}
