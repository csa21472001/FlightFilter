package main;

import main.model.Flight;
import main.service.FlightBuilder;
import main.service.FlightFilter;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        FlightFilter flightFilter = new FlightFilter();

        System.out.println("Весь список пролетов");
        printFlight(flights);

        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println("Исключить вылет до текущего момента");
        printFlight(flightFilter.filterFlightsByDepartureAfterNow(flights,dateTime));

        System.out.println("Исключить сегменты с датой прилёта раньше даты вылета");
        printFlight(flightFilter.filterFlightsWithoutArrivalBeforeDepature(flights));

        System.out.println("Исключить перелеты, где общее время, проведённое на земле, " +
                "превышает два часа");
        printFlight(flightFilter.filterFlightsByTheGroundTimeNoMoreThanTwoHours(flights));

    }
    public static void printFlight(List<Flight> flights) {
        flights.forEach(System.out::println);
    }
}
