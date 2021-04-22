package com.example.demo.model;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Component
public class FlightModel implements Serializable {

    @NotEmpty
    @NotNull
    private String numberOfPassangers;

    @NotEmpty
    @NotNull
    private String currency;

    @NotEmpty
    @NotNull
    private String fromLocation;

    @NotEmpty
    @NotNull
    private String toLocation;

    private String departure;
    private String arrival;
    private int numberOfBookableSeats;
    private String totalPrice;
    private String numberOfStops;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String dateOfFlight;

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getNumberOfStops() {
        return numberOfStops;
    }

    public void setNumberOfStops(String numberOfStops) {
        this.numberOfStops = numberOfStops;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public int getNumberOfBookableSeats() {
        return numberOfBookableSeats;
    }

    public void setNumberOfBookableSeats(int numberOfBookableSeats) {
        this.numberOfBookableSeats = numberOfBookableSeats;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }


    public String getNumberOfPassangers() {
        return numberOfPassangers;
    }

    public void setNumberOfPassangers(String numberOfPassangers) {
        this.numberOfPassangers = numberOfPassangers;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDateOfFlight() {
        return dateOfFlight;
    }

    public void setDateOfFlight(String dateOfFlight) {
        this.dateOfFlight = dateOfFlight;
    }

    @Override
    public String toString() {
        return "FlightModel{" +
                "numberOfPassangers='" + numberOfPassangers + '\'' +
                ", currency='" + currency + '\'' +
                ", fromLocation='" + fromLocation + '\'' +
                ", toLocation='" + toLocation + '\'' +
                ", departure='" + departure + '\'' +
                ", arrival='" + arrival + '\'' +
                ", numberOfBookableSeats=" + numberOfBookableSeats +
                ", totalPrice='" + totalPrice + '\'' +
                ", numberOfStops='" + numberOfStops + '\'' +
                ", dateOfFlight='" + dateOfFlight + '\'' +
                '}';
    }
}
