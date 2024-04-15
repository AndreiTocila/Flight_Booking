package com.hcl.bookingservice.domain;

import com.hcl.bookingservice.dto.CardDetailsDTO;
import com.hcl.bookingservice.dto.FlightDetailsDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Objects;

@Document
public class Booking
{
    @Id
    private String id;
    private FlightDetailsDTO flight;
    private CardDetailsDTO cardDetails;
    private Long numberOfSeats;
    private String userId;
    private String status;
    private LocalDateTime expirationDate;

    public Booking()
    {
    }

    public Booking(String id, FlightDetailsDTO flight, CardDetailsDTO cardDetails, Long numberOfSeats, String userId, String status, LocalDateTime expirationDate)
    {
        this.id = id;
        this.flight = flight;
        this.cardDetails = cardDetails;
        this.numberOfSeats = numberOfSeats;
        this.userId = userId;
        this.status = status;
        this.expirationDate = expirationDate;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public FlightDetailsDTO getFlight()
    {
        return flight;
    }

    public void setFlight(FlightDetailsDTO flight)
    {
        this.flight = flight;
    }

    public CardDetailsDTO getCardDetails()
    {
        return cardDetails;
    }

    public void setCardDetails(CardDetailsDTO cardDetails)
    {
        this.cardDetails = cardDetails;
    }

    public Long getNumberOfSeats()
    {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Long numberOfSeats)
    {
        this.numberOfSeats = numberOfSeats;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public LocalDateTime getExpirationDate()
    {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate)
    {
        this.expirationDate = expirationDate;
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
        Booking booking = (Booking) o;
        return Objects.equals(id, booking.id) && Objects.equals(flight, booking.flight) && Objects.equals(cardDetails, booking.cardDetails) && Objects.equals(numberOfSeats, booking.numberOfSeats) && Objects.equals(userId, booking.userId) && Objects.equals(status, booking.status) && Objects.equals(expirationDate, booking.expirationDate);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, flight, cardDetails, numberOfSeats, userId, status, expirationDate);
    }

    @Override
    public String toString()
    {
        return "Booking{" +
                "id='" + id + '\'' +
                ", flight=" + flight +
                ", cardDetails=" + cardDetails +
                ", numberOfSeats=" + numberOfSeats +
                ", userId='" + userId + '\'' +
                ", status='" + status + '\'' +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
