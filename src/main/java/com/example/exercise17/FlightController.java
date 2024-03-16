package com.example.exercise17;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    @GetMapping("/provision")
    public List<Flight> provisionFlights(@RequestParam(required = false, defaultValue = "100") int n) {
        List<Flight> provisionedFlights = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            Flight flight = new Flight();
            flight.setFromAirport("Some Airport");
            flight.setToAirport("Another Airport");
            flight.setStatus("ONTIME");
            provisionedFlights.add(flight);
        }

        return provisionedFlights;
    }
    @GetMapping("/all")
    public List<Flight> getAllFlights() {
        return flightRepository.findAll(Sort.by("fromAirport").ascending());
    }

    @GetMapping("/ontime")
    public List<Flight> getOnTimeFlights() {
        return flightRepository.findByStatus("ONTIME");
    }

    @GetMapping("/custom")
    public List<Flight> getCustomFlights(@RequestParam String p1, @RequestParam String p2) {
        List<String> statuses = new ArrayList<>();
        statuses.add(p1);
        statuses.add(p2);
        return flightRepository.findByStatusIn(statuses);
    }

}