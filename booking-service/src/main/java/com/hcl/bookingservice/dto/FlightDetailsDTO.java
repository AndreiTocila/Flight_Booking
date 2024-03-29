package com.hcl.bookingservice.dto;

import java.util.Objects;

public class FlightDetailsDTO
{
    private Long id;

    private String departure;

    private String arrival;

    public FlightDetailsDTO()
    {
    }

    public FlightDetailsDTO(Long id, String departure, String arrival)
    {
        this.id = id;
        this.departure = departure;
        this.arrival = arrival;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getDeparture()
    {
        return departure;
    }

    public void setDeparture(String departure)
    {
        this.departure = departure;
    }

    public String getArrival()
    {
        return arrival;
    }

    public void setArrival(String arrival)
    {
        this.arrival = arrival;
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
        FlightDetailsDTO that = (FlightDetailsDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(departure, that.departure) && Objects.equals(arrival, that.arrival);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, departure, arrival);
    }

    @Override
    public String toString()
    {
        return "FlightDetailsDTO{" +
                "id=" + id +
                ", departure='" + departure + '\'' +
                ", arrival='" + arrival + '\'' +
                '}';
    }
}
