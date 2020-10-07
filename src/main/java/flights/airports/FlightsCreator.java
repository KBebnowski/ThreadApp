//Klasa uzyta aby nie powielac kodu. Wykorzystywana przez BerlinFlightsCreator, LondonFlightsCreator itd...,
//przypisuje pozostale dane do obiektu i przesyla kopie adresu obiektu do metody sendOut klasy FlightCenter,
//gdzie zostanie stworozny osobny obiekt dla kazdego lotu (odejscie od flyweight)
//Uzyto synchronized aby zapobiec wspoldzielenia metody przez wyzej wspomniane klasy

package flights.airports;

import flights.FlightCenter;
import flights.airplaneService.AirplaneService;
import flights.app;

import java.util.Random;

/**
 * Created by Kamil on 25.09.2020.
 */
public class FlightsCreator {

    public static synchronized void createFly(AirplaneService airplane) {
        FlightCenter.incrementFlightNumber();                                               //dodanie numeru lotu
        airplane.setFlightNumber(FlightCenter.getFlightNumber());                           //przypisanie numeru lotu
        airplane.setFlightTime((getTime()));                                                //przypisanie czasu lotu
        airplane.setDepartureTime((System.currentTimeMillis()+airplane.getFlightTime()));   //przypisanie czasu przylotu

        FlightCenter.sendOut(airplane);                                                     //wywolanie metody, ktora stworzy osobny obiekt dla kazdego lotu
        app.open();                                                                         //wywolanie closeLatch.countDown()
    }

    private static long getTime(){
        long time = 0;
        Random random = new Random();
        time = random.nextInt(15000)+2000;                                           //czas lotu od 2 do 17 sekund
        return time;
    }
}
