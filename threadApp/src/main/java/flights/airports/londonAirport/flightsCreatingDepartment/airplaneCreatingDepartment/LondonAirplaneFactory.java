//Klasa odpowiedzialna za sprawdzanie lotow i tworzenie nowych jesli dany lot nie istnieje - flyweight

package flights.airports.londonAirport.flightsCreatingDepartment.airplaneCreatingDepartment;

import flights.airplaneService.AirplaneService;
import flights.airports.londonAirport.flightsCreatingDepartment.airplaneCreatingDepartment.services.LondonToBerlinAirplaneService;
import flights.airports.londonAirport.flightsCreatingDepartment.airplaneCreatingDepartment.services.LondonToParisAirplaneService;

import flights.airports.londonAirport.flightsCreatingDepartment.airplaneCreatingDepartment.services.LondonToWarsawAirplaneService;

import java.util.HashMap;

/**
 * Created by Kamil on 06.08.2020.
 */
public class LondonAirplaneFactory {

    private static HashMap<String, AirplaneService> airplanes = new HashMap<String, AirplaneService>();

    public static AirplaneService getAirplane(String flight){

        AirplaneService airplane = null;

        if(airplanes.get(flight)!=null){
            airplane=airplanes.get(flight);

        }else{
            switch(flight){
                case "LondonToBerlin":
                    System.out.println("Flight from London to Berlin created");
                    System.out.println("---------------------");
                    airplane = new LondonToBerlinAirplaneService();
                    break;
                case "LondonToParis":
                    System.out.println("Flight from London to Paris created");
                    System.out.println("---------------------");
                    airplane = new LondonToParisAirplaneService();
                    break;
                case "LondonToWarsaw":
                    System.out.println("Flight from London to Warsaw created");
                    System.out.println("---------------------");
                    airplane = new LondonToWarsawAirplaneService();
                    break;
                default:
                    System.out.println("This connection doesn't exist in our airlines");
            }

            airplanes.put(flight, airplane);
        }

        return airplane;
    }
}
