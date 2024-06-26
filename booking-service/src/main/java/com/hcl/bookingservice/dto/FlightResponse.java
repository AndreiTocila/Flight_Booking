package com.hcl.bookingservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class FlightResponse
{
    @JsonProperty("data")
    private FlightDetailsDTO flightDetailsDTO;

    public FlightResponse()
    {
    }

    public FlightResponse(FlightDetailsDTO flightDetailsDTO)
    {
        this.flightDetailsDTO = flightDetailsDTO;
    }

    public FlightDetailsDTO getFlightDetailsDTO()
    {
        return flightDetailsDTO;
    }

    public void setFlightDetailsDTO(FlightDetailsDTO flightDetailsDTO)
    {
        this.flightDetailsDTO = flightDetailsDTO;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        FlightResponse that = (FlightResponse) o;
        return Objects.equals(flightDetailsDTO, that.flightDetailsDTO);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(flightDetailsDTO);
    }

    @Override
    public String toString()
    {
        return "FlightResponse{" +
                "flightDetailsDTO=" + flightDetailsDTO +
                '}';
    }
}
