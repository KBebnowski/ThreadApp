package flights.airports.berlinAirport.flightsCreatingDepartment.airplaneCreatingDepartment.services;

import flights.airplaneService.AbstractAirplaneService;

/**
 * Created by Kamil on 06.08.2020.
 */
public class BerlinToParisAirplaneService extends AbstractAirplaneService {

    public String getPlaceOfArrival() {
        return "Paris";
    }

    public String getPlaceOfStart() {
        return "Berlin";
    }
}
