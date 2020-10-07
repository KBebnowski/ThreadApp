package flights.airports.londonAirport.flightsCreatingDepartment.airplaneCreatingDepartment.services;

import flights.airplaneService.AbstractAirplaneService;

/**
 * Created by Kamil on 06.08.2020.
 */
public class LondonToParisAirplaneService extends AbstractAirplaneService {

    public String getPlaceOfArrival() {
        return "Paris";
    }

    public String getPlaceOfStart() {
        return "London";
    }

}
