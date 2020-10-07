//Klasa odpowiedzialna za tworzenie osobnego obiektu dla kazdego lotu (w celach nauki wczesniej uzylem wzorca projektowego flyweight)

package flights;

import flights.airplaneService.AirplaneService;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Kamil on 11.09.2020.
 */
public class FlightCenter {

    public static Lock lockForClose = new ReentrantLock();          //pole w klasie FlightCenter aby wszystkie Klasy FlightControl mialy do niej dostep

    private static Object lock1 = new Object();                     //pole, ktore posluzy do zablokowania watku w metodzie incrementFlightNumber(), tak naprawde nie potrzebne, poniewaz metoda - FlightsCreator.createFly,
                                                                    //w ktorej uzyte jest incrementFlightNumber() jest synchronized, ale w przyszlosci metoda, incrementFlightNumber, moze zostac uzyta w innym miejscu programu

    private static int flightNumber;                                //pole przechowujace numer lotu

    public static int getFlightNumber() {
        return flightNumber;
    }

    public static void incrementFlightNumber() {

        synchronized (lock1) {
           try{
               Thread.sleep(100);
           }catch(InterruptedException e){
               e.printStackTrace();
           }
            flightNumber++;
        }
    }

    public static void restartFlightNumber(){
        flightNumber = 0;
    }

    public synchronized static void sendOut(AirplaneService airplane){

            Airplane plane = new Airplane();
            plane.setPlaceOfStart(airplane.getPlaceOfStart());
            plane.setPlaceOfArrival(airplane.getPlaceOfArrival());
            plane.setDepartureTime(airplane.getDepartureTime());
            plane.setFlightNumber(airplane.getFlightNumber());
            plane.setFlightTime(airplane.getFlightTime());

            AbstractFlightControl.producer(plane);
    }
}
