package com.example.demo.controller;

import com.amadeus.exceptions.ResponseException;
import com.example.demo.model.FlightModel;
import com.example.demo.service.FlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller("/")
public class FlightController {

    private static final Logger LOG = LoggerFactory.getLogger(FlightController.class);

    @Value("${spring.application.name}")
    String appName;

    @Autowired
    private FlightService flightService;

    @GetMapping
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }

    @PostMapping("/getFlightResults")
    public String getFlightResults(@Valid @ModelAttribute FlightModel flightModel, Model model, BindingResult result) throws ResponseException {

        LOG.info("FlightModel model: " + flightModel);
        List<FlightModel> flights = flightService.searchAllFlights(flightModel);
        model.addAttribute("flights", flights);

        return "flightOffer";
    }

}
