package com.hcl.flightsearch.model;

import java.time.LocalDateTime;

public class SearchCriteria {

    private String operatorName;
    private String departureAirport;
    private String arrivalAirport;
    private LocalDateTime departureFrom;
    private LocalDateTime departureTo;

    public SearchCriteria(String operatorName, String departureAirport, String arrivalAirport, LocalDateTime departureFrom, LocalDateTime departureTo) {
        this.operatorName = operatorName;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureFrom = departureFrom;
        this.departureTo = departureTo;
    }

    public SearchCriteria() {
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public LocalDateTime getDepartureFrom() {
        return departureFrom;
    }

    public void setDepartureFrom(LocalDateTime departureFrom) {
        this.departureFrom = departureFrom;
    }

    public LocalDateTime getDepartureTo() {
        return departureTo;
    }

    public void setDepartureTo(LocalDateTime departureTo) {
        this.departureTo = departureTo;
    }
}
