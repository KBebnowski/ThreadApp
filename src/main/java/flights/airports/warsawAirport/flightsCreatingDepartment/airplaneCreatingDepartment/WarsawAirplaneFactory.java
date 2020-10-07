//Klasa odpowiedzialna za sprawdzanie lotow i tworzenie nowych jesli dany lot nie istnieje - flyweight

package flights.airports.warsawAirport.flightsCreatingDepartment.airplaneCreatingDepartment;

import flights.airplaneService.AirplaneService;
import flights.airports.warsawAirport.flightsCreatingDepartment.airplaneCreatingDepartment.services.WarsawToBerlinAirplaneService;
import flights.airports.warsawAirport.flightsCreatingDepartment.airplaneCreatingDepartment.services.WarsawToLondonAirplaneService;
import flights.airports.warsawAirport.flightsCreatingDepartment.airplaneCreatingDepartment.services.WarsawToParisAirplaneService;

import java.util.HashMap;

/**
 * Created by Kamil on 06.08.2020.
 */
public class WarsawAirplaneFactory {

    private static HashMap<String, AirplaneService> airplanes = new HashMap<String, AirplaneService>();

    public static AirplaneService getAirplane(String flight){

        AirplaneService airplane = null;

        if(airplanes.get(flight)!=null){
            airplane=airplanes.get(flight);

        }else{
            switch(flight){
                case "WarsawToLondon":
                    System.out.println("Flight from Warsaw to London created");
                    System.out.println("---------------------");
                    airplane = new WarsawToLondonAirplaneService();
                    break;
                case "WarsawToBerlin":
                    System.out.println("Flight from Warsaw to Berlin created");
                    System.out.println("---------------------");
                    airplane = new WarsawToBerlinAirplaneService();
                    break;
                case "WarsawToParis":
                    System.out.println("Flight from Warsaw to Paris created");
                    System.out.println("---------------------");
                    airplane = new WarsawToParisAirplaneService();
                    break;
                default:
                    System.out.println("This connection doesn't exist in our airlines");
            }

            airplanes.put(flight, airplane);
        }

        return airplane;
    }
}
