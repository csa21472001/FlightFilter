package test;
import main.model.Flight;
import main.service.FlightBuilder;
import main.service.FlightFilter;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightFilterTest {
    private final FlightFilter flightFilter = new FlightFilter();
    private final LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);
    private final Flight normalFlight = FlightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2));
    private final Flight pastDepartingFlight = FlightBuilder.createFlight(threeDaysFromNow.minusDays(6), threeDaysFromNow);
    private final Flight backToTheFutureFlight = FlightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.minusHours(6));
    private final Flight multiSegmentMoreThanTwoHoursFlight = FlightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(4),
                                threeDaysFromNow.plusHours(6), threeDaysFromNow.plusHours(7));
    @Test
    void filterFlightsByDepartureAfterNow_flightList_correctFlightList() {
        LocalDateTime lcl = LocalDateTime.now();
        List<Flight> flights = flightFilter.filterFlightsByDepartureAfterNow(List.of(normalFlight, pastDepartingFlight,backToTheFutureFlight),lcl);
        assertEquals(List.of(normalFlight,backToTheFutureFlight), flights);
    }
    @Test
    void filterFlightsWithoutArrivalBeforeDepature_flightList_correctFlightList() {
        List<Flight> flights = flightFilter.filterFlightsWithoutArrivalBeforeDepature(List.of(normalFlight, pastDepartingFlight,backToTheFutureFlight));
        assertEquals(List.of(normalFlight, pastDepartingFlight), flights);
    }
    @Test
    void filterFlightsByTheGroundTimeNoMoreThanTwoHours_flightList_correctFlightList() {
        List<Flight> flights = flightFilter.filterFlightsByTheGroundTimeNoMoreThanTwoHours(List.of(multiSegmentMoreThanTwoHoursFlight, pastDepartingFlight,backToTheFutureFlight));
        assertEquals(List.of(pastDepartingFlight,backToTheFutureFlight), flights);
    }

}
