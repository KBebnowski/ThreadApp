//Klasa przeznaczona do tworzenia wielu lotow - flyweight

package flights.airports.londonAirport.flightsCreatingDepartment;

import flights.airplaneService.AirplaneService;
import flights.airports.FlightsCreator;
import flights.airports.londonAirport.flightsCreatingDepartment.airplaneCreatingDepartment.LondonAirplaneFactory;

import java.util.Random;

/**
 * Created by Kamil on 06.08.2020.
 */
public class LondonFlightsCreator {

    protected static String flights[] = {"LondonToBerlin", "LondonToParis", "LondonToWarsaw"};

    public static void createFly() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i<10; i++){

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    AirplaneService airplane = LondonAirplaneFactory.getAirplane(getFlight());                          //nie tworzymy nowego obiektu jesli taki lot juz istnieje - wzorzec projektowy flyweight

                    FlightsCreator.createFly(airplane);
                }
            }
        });
        t1.start();
    }

    private static String getFlight() {
        Random random = new Random();
        int flight = random.nextInt(flights.length);
        return flights[flight];
    }
}
