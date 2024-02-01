package main.service;

import main.model.Flight;

import java.time.LocalDateTime;
import java.util.List;

public interface BasicFlightFilter {
    List<Flight> filterFlightsByDepartureAfterNow(List<Flight> flights, LocalDateTime dateTime);
    List<Flight> filterFlightsWithoutArrivalBeforeDepature(List<Flight> flights);
    List<Flight> filterFlightsByTheGroundTimeNoMoreThanTwoHours(List<Flight> flights);
}
