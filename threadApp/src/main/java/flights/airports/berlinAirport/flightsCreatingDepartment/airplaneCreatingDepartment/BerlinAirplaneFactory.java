//Klasa odpowiedzialna za sprawdzanie lotow i tworzenie nowych jesli dany lot nie istnieje - flyweight

package flights.airports.berlinAirport.flightsCreatingDepartment.airplaneCreatingDepartment;

import flights.airplaneService.AirplaneService;
import flights.airports.berlinAirport.flightsCreatingDepartment.airplaneCreatingDepartment.services.BerlinToLondonAirplaneService;
import flights.airports.berlinAirport.flightsCreatingDepartment.airplaneCreatingDepartment.services.BerlinToParisAirplaneService;
import flights.airports.berlinAirport.flightsCreatingDepartment.airplaneCreatingDepartment.services.BerlinToWarsawAirplaneService;

import java.util.HashMap;

/**
 * Created by Kamil on 06.08.2020.
 */
public class BerlinAirplaneFactory {

    private static HashMap<String, AirplaneService> airplanes = new HashMap<String, AirplaneService>();

    public static AirplaneService getAirplane(String flight){

        AirplaneService airplane = null;

        if(airplanes.get(flight)!=null){
            airplane=airplanes.get(flight);
        }else{
            switch(flight){
                case "BerlinToLondon":
                    System.out.println("Flight from Berlin to London created");
                    System.out.println("---------------------");
                    airplane = new BerlinToLondonAirplaneService();
                    break;
                case "BerlinToParis":
                    System.out.println("Flight from Berlin to Paris created");
                    System.out.println("---------------------");
                    airplane = new BerlinToParisAirplaneService();
                    break;
                case "BerlinToWarsaw":
                    System.out.println("Flight from Berlin to Warsaw created");
                    System.out.println("---------------------");
                    airplane = new BerlinToWarsawAirplaneService();
                    break;
                default:
                    System.out.println("This connection doesn't exist in our airlines");
            }

            airplanes.put(flight, airplane);
        }

        return airplane;
    }
}

