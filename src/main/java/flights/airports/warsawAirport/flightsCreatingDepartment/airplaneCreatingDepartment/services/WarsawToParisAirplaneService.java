package flights.airports.warsawAirport.flightsCreatingDepartment.airplaneCreatingDepartment.services;

import flights.airplaneService.AbstractAirplaneService;

/**
 * Created by Kamil on 06.08.2020.
 */
public class WarsawToParisAirplaneService extends AbstractAirplaneService {

    public String getPlaceOfArrival() {
        return "Paris";
    }

    public String getPlaceOfStart() {
        return "Warsaw";
    }
}
