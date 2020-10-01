//Klasa odpowiedzialna za sprawdzanie lotow i tworzenie nowych jesli dany lot nie istnieje - flyweight

package flights.airports.parisAirport.flightsCreatingDepartment.airplaneCreatingDepartment;

import flights.airplaneService.AirplaneService;
import flights.airports.parisAirport.flightsCreatingDepartment.airplaneCreatingDepartment.services.ParisToBerlinAirplaneService;
import flights.airports.parisAirport.flightsCreatingDepartment.airplaneCreatingDepartment.services.ParisToLondonAirplaneService;
import flights.airports.parisAirport.flightsCreatingDepartment.airplaneCreatingDepartment.services.ParisToWarsawAirplaneService;

import java.util.HashMap;

/**
 * Created by Kamil on 06.08.2020.
 */
public class ParisAirplaneFactory {

    private static HashMap<String, AirplaneService> airplanes = new HashMap<String, AirplaneService>();

    public static AirplaneService getAirplane(String flight){

        AirplaneService airplane = null;

        if(airplanes.get(flight)!=null){
            airplane=airplanes.get(flight);

        }else{
            switch(flight){
                case "ParisToLondon":
                    System.out.println("Flight from Paris to London created");
                    System.out.println("---------------------");
                    airplane = new ParisToLondonAirplaneService();
                    break;
                case "ParisToBerlin":
                    System.out.println("Flight from Paris to Berlin created");
                    System.out.println("---------------------");
                    airplane = new ParisToBerlinAirplaneService();
                    break;
                case "ParisToWarsaw":
                    System.out.println("Flight from Paris to Warsaw created");
                    System.out.println("---------------------");
                    airplane = new ParisToWarsawAirplaneService();
                    break;
                default:
                    System.out.println("This connection doesn't exist in our airlines");
            }

            airplanes.put(flight, airplane);
        }

        return airplane;
    }
}
