package flights.airplaneService;

/**
 * Created by Kamil on 05.08.2020.
 */
public interface AirplaneService {

    String getPlaceOfArrival();
    String getPlaceOfStart();
    void setFlightNumber(int flightNumber);
    int getFlightNumber();
    void setFlightTime(long flightTime);
    long getFlightTime();
    void setDepartureTime(long departureTime);
    long getDepartureTime();
}

