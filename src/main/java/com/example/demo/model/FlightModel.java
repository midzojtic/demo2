package com.example.demo.model;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Component
@Data
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

}
