//klasa stworzona aby nie powielac kodu, uzywana przez BerlinFlightControl, LondonFlightControl itd..
//Dodaje loty do listy w metodzie producer. Natomiast w metodzie consumer() sprawdza liste -> wyswietla samoloty, ktore dolecialy i usuwa je z listy
//uzyto: ReentrantLock() wywolane z klasy FlightCenter.lockForClose; CountDownLatch - app.close(); synchronized(lock)
//uzyto producer() consumer(), jednak bez ArrayBlockingQueue tylko na ArrayList, poniewaz kod nie wykonywal sie poprawnie

package flights;

import flights.airplaneService.AirplaneService;

import java.util.ArrayList;

/**
 * Created by Kamil on 25.09.2020.
 */
public abstract class AbstractFlightControl {

    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    private static ArrayList<AirplaneService> list = new ArrayList<>();

    public static void producer(AirplaneService airplane){

        synchronized(lock1) {
            list.add(airplane);
        }
    }

    public  static void consumer(){

        synchronized(lock2){

            int i = 0;

            while(true) {
                if (i == (list.size()-1)) {
                    i = 0;
                }

                try {
                    if (list.get(i).getDepartureTime() <= System.currentTimeMillis()) {
                        System.out.println("Samolot o numerze lotu " + list.get(i).getFlightNumber() + " z lotniska " +
                                list.get(i).getPlaceOfStart() + " wyladowal " + " w " + list.get(i).getPlaceOfArrival() +
                                "\t  ||  Czas lotu - " + (list.get(i).getFlightTime()/1000) + " sekund");
                        list.remove(i);
                    } else {
                        i++;
                    }
                } catch (IndexOutOfBoundsException e) {

                    FlightCenter.lockForClose.lock();
                    app.close();
                    FlightCenter.lockForClose.unlock();
                    break;
                }
            }
        }
    }
}
