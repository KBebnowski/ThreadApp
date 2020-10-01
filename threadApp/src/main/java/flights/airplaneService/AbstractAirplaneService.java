package flights.airplaneService;

/**
 * Created by Kamil on 07.08.2020.
 */
public abstract class AbstractAirplaneService implements AirplaneService {

    private int flightNumber;
    private long flightTime;
    private long departureTime;

    @Override
    public abstract String getPlaceOfArrival();

    @Override
    public abstract String getPlaceOfStart();

    @Override
    public void setFlightNumber(int flightNumber) {
        this.flightNumber =flightNumber;
    }

    @Override
    public int getFlightNumber() {
        return flightNumber;
    }

    @Override
    public void setFlightTime(long flightTime) {
        this.flightTime=flightTime;
    }

    @Override
    public long getFlightTime() {
        return flightTime;
    }

    @Override
    public void setDepartureTime(long departureTime){
        this.departureTime = departureTime;
    }

    @Override
    public long getDepartureTime(){
        return departureTime;
    }
}
