import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class FlightFilter {

    public List<Flight> filterFlightsByDepartureAfterNow(List<Flight> flights, LocalDateTime dateTime) {
        return flights.stream().filter(flight ->
                flight.getSegments().stream().allMatch(segment -> segment.getDepartureDate().isAfter(dateTime)))
                .collect(Collectors.toList());
    }

    public List<Flight> filterFlightsWithoutArrivalBeforeDepature(List<Flight> flights) {
        return flights.stream().filter(flight ->
                flight.getSegments().stream().allMatch(segment -> segment.getArrivalDate().isAfter(segment.getDepartureDate())))
                .collect(Collectors.toList());
    }

    public List<Flight> filterFlightsByTheGroundTimeNoMoreThanTwoHours(List<Flight> flights) {
        return flights.stream().filter(flight -> {
                    List<Segment> segments = flight.getSegments();
                    long groundTime = 0;
                    for (int i = 0; i < segments.size(); i++) {
                        LocalDateTime firstFlight = segments.get(i).getArrivalDate();
                        if (i + 1 < segments.size()) {
                            LocalDateTime nextflight = segments.get(i + 1).getDepartureDate();
                            groundTime = Duration.between(firstFlight,nextflight).toHours();
                        }
                        if (groundTime > 2) {
                            return false;
                        }
                    }
                    return true;
        }).collect(Collectors.toList());
    }
}
