package com.hcl.bookingservice.dto;

import java.util.Objects;

public class BookingDTO
{
    private Long flightId;
    private Long numberOfSeats;

    public BookingDTO()
    {
    }

    public BookingDTO(Long flightId, Long numberOfSeats)
    {
        this.flightId = flightId;
        this.numberOfSeats = numberOfSeats;
    }

    public Long getFlightId()
    {
        return flightId;
    }

    public void setFlightId(Long flightId)
    {
        this.flightId = flightId;
    }

    public Long getNumberOfSeats()
    {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Long numberOfSeats)
    {
        this.numberOfSeats = numberOfSeats;
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
        BookingDTO that = (BookingDTO) o;
        return Objects.equals(flightId, that.flightId) && Objects.equals(numberOfSeats, that.numberOfSeats);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(flightId, numberOfSeats);
    }

    @Override
    public String toString()
    {
        return "BookingDTO{" +
                "flightId=" + flightId +
                ", numberOfSeats=" + numberOfSeats +
                '}';
    }
}
