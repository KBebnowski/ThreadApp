package flights.airports.parisAirport.flightsCreatingDepartment.airplaneCreatingDepartment.services;

import flights.airplaneService.AbstractAirplaneService;

/**
 * Created by Kamil on 06.08.2020.
 */
public class ParisToWarsawAirplaneService extends AbstractAirplaneService {

    public String getPlaceOfArrival() {
        return "Warsaw";
    }

    public String getPlaceOfStart() {
        return "Paris";
    }
}
