package com.hcl.bookingservice.dto;

import java.util.Objects;

public class BookingDTO
{
    private Long flightId;
    private Long cardDetailsId;
    private Long numberOfSeats;

    public BookingDTO()
    {
    }

    public BookingDTO(Long flightId, Long cardDetailsId, Long numberOfSeats)
    {
        this.flightId = flightId;
        this.cardDetailsId = cardDetailsId;
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

    public Long getCardDetailsId()
    {
        return cardDetailsId;
    }

    public void setCardDetailsId(Long cardDetailsId)
    {
        this.cardDetailsId = cardDetailsId;
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
        return Objects.equals(flightId, that.flightId) && Objects.equals(cardDetailsId, that.cardDetailsId) && Objects.equals(numberOfSeats, that.numberOfSeats);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(flightId, cardDetailsId, numberOfSeats);
    }

    @Override
    public String toString()
    {
        return "BookingDTO{" +
                "flightId=" + flightId +
                ", cardDetailsId=" + cardDetailsId +
                ", numberOfSeats=" + numberOfSeats +
                '}';
    }
}
