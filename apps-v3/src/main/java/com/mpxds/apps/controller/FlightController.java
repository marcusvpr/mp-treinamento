package com.mpxds.apps.controller;

import com.mpxds.apps.model.Flight;
import com.mpxds.apps.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.faces.view.ViewScoped;
//import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// @Named
@ViewScoped
@Controller
public class FlightController implements Serializable {
    //
    private Flight flight = new Flight();

    private List<Flight> flights = new ArrayList<>();

    @Autowired
    private FlightRepository flightRepository;

    // ---

    public void fetchAll() {
        //
        flights = flightRepository.findAll();
    }

    public void save() { 
        //
        flightRepository.save(flight);
        
        flight = new Flight();
        flights = flightRepository.findAll();
    }

    public void edit(Flight flight) {
        //
        this.flight = flight;
    }

    public void refresh() { 
        //
        flight = new Flight();
    }

    public List<Flight> getFlights() {
        //
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        //
        this.flights = flights;
    }

    public Flight getFlight() {
        //
        return flight;
    }

    public void setFlight(Flight flight) {
        //
        this.flight = flight;
    }

}