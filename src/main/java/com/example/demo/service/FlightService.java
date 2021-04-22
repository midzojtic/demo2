package com.example.demo.service;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import com.example.demo.dao.DBase;
import com.example.demo.model.FlightModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FlightService {

    private static final Logger LOG = LoggerFactory.getLogger(FlightService.class);
    private final Amadeus amadeus = Amadeus.builder("DCV45ow65sJ89qwBKaSGElC7utxmPFBb", "srSOd9dF9PmwYlGG").build();

    @Autowired
    private DBase dbBaze;

    /**
     * This method fetches all the data from flight records
     *
     * @return all flights
     */
    public List<FlightModel> searchAllFlights(FlightModel model) throws ResponseException {

        LOG.debug("Accessed getAllFlights");

        String key = model.getFromLocation() + model.getToLocation() + model.getDateOfFlight() + model.getCurrency() + model.getNumberOfPassangers();
        List<FlightModel> flightModelList = dbBaze.getList(key);

        LOG.debug("before: " + flightModelList);

        if (flightModelList != null && !flightModelList.isEmpty()) {
            LOG.debug("Already has result");
            return flightModelList;
        }

        flightModelList = new ArrayList<>();

        FlightOfferSearch[] flightOffersSearches = amadeus.shopping.flightOffersSearch.get(
                Params.with("originLocationCode", model.getFromLocation())
                        .and("destinationLocationCode", model.getToLocation())
                        .and("departureDate", model.getDateOfFlight())
                        .and("currencyCode", model.getCurrency())
                        .and("adults", model.getNumberOfPassangers())
                        .and("max", 3));

        if (flightOffersSearches == null || flightOffersSearches.length == 0) {
            LOG.debug("No flight offers for this combination, returning...");
            return Collections.emptyList();
        }


        for (int i = 0; i < flightOffersSearches.length; i++) {

            FlightOfferSearch flightOfferSearch = flightOffersSearches[i];

            LOG.debug("flightOfferSearch: " + flightOfferSearch);

            FlightOfferSearch.Itinerary[] itineraries = flightOfferSearch.getItineraries();
            FlightModel flightModel = new FlightModel();

            flightModel.setCurrency(model.getCurrency());
            flightModel.setDateOfFlight(model.getDateOfFlight());
            flightModel.setFromLocation(model.getFromLocation());
            flightModel.setToLocation(model.getToLocation());
            flightModel.setNumberOfBookableSeats(flightOfferSearch.getNumberOfBookableSeats());

            FlightOfferSearch.SearchSegment[] segments = itineraries[0].getSegments();
            flightModel.setArrival(segments[0].getArrival().getAt());
            flightModel.setDeparture(segments[0].getDeparture().getAt());
            flightModel.setNumberOfStops("" + segments[0].getNumberOfStops());
            flightModel.setTotalPrice("" + flightOfferSearch.getPrice().getGrandTotal());

            LOG.debug("FlightModel number: {}", i);
            LOG.debug("FlightModel: {}", flightModel);

            flightModelList.add(flightModel);

        }

        dbBaze.saveData(key, flightModelList);

        return flightModelList;
    }


}
