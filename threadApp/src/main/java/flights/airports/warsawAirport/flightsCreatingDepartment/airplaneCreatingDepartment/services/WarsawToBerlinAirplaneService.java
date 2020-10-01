package flights.airports.warsawAirport.flightsCreatingDepartment.airplaneCreatingDepartment.services;

import flights.airplaneService.AbstractAirplaneService;

/**
 * Created by Kamil on 06.08.2020.
 */
public class WarsawToBerlinAirplaneService extends AbstractAirplaneService {

    public String getPlaceOfArrival() {
        return "Berlin";
    }

    public String getPlaceOfStart() {
        return "Warsaw";
    }
}
