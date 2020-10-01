package flights;
/**
 * Created by Kamil on 05.08.2020.
 */

import flights.airports.berlinAirport.flightsCreatingDepartment.BerlinFlightsCreator;
import flights.airports.berlinAirport.flightsReceivingDepartment.BerlinFlightControl;
import flights.airports.londonAirport.flightsCreatingDepartment.LondonFlightsCreator;
import flights.airports.londonAirport.flightsReceivingDepartment.LondonFlightControl;
import flights.airports.parisAirport.flightsCreatingDepartment.ParisFlightsCreator;
import flights.airports.parisAirport.flightsReceivingDepartment.ParisFlightControl;
import flights.airports.warsawAirport.flightsCreatingDepartment.WarsawFlightsCreator;
import flights.airports.warsawAirport.flightsReceivingDepartment.WarsawFlightControl;
import flights.hospitals.FujitsuHospitalDepartment;
import flights.hospitals.TestingDepartment;
import flights.who.CovidDepartment;

import java.util.concurrent.*;

//Producer Consumer -> BlockingQueue queue = ArrayBlockingQueue -> lista ktora nie jest dzielona przez watki (chroniona)

//w forze dodawaj do queue losowe true lub false na produce consume to zrob
public class app {

    public static CountDownLatch openLatch;
    public static CountDownLatch closeLatch;
    private static boolean lockdown=false;

    public static void main(String[] args) {

        CovidDepartment covidDepartment = new CovidDepartment();

        int days = 1;

        while (lockdown == false) {
            openLatch = new CountDownLatch(40);                                                         //dopoki openLatch nie zostanie wywolany 40 razy program nie przejdzie dalej za metode await()
            closeLatch = new CountDownLatch(4);

            System.out.println("Dzien " + days + "\n\nLOTNISKA FUJITSU OTWARTE \n\nSystem airports wlaczony \n\nTWORZENIE LOTOW ");

            loadingScreen();

            // Wzorzec projektowy flyweight
            BerlinFlightsCreator.createFly();
            LondonFlightsCreator.createFly();
            ParisFlightsCreator.createFly();
            WarsawFlightsCreator.createFly();

            // Zastosowalem tylko jeden try{}catch{}, poniewaz jesli, w ktoryms momencie po 68 linijce (Thread.sleep(2000),
            // lub 72 (closeLatch.await()) zostalby wyrzucony wyjatek to dalsza czesc programu by sie nie wykonala,
            // dlatego zastosowalem final. Jest tylko 1 catch bo wszystkie wyrzucaly taki sam wyjatek.
            try {
                openLatch.await();

                System.out.println("WSZYSTKIE SAMOLOTY WYSTARTOWALY (arraylisty zostaly zapelnione)");
                System.out.println("\n\n\n\n\n\n");

                Thread.sleep(2000);

                runFlightControls();                                                                        //uruchamia watki, ktore sprawdzaja (liste) czy dany samolot wyladowal w danym lotnisku

                closeLatch.await();

                System.out.println("\n\n");
                System.out.println("BRAK LOTOW, WSZYSTKIE LOTNISKA FUJITSU ZAMKNIETE\n\n\n\n\n\n");

                Thread t1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        covidDepartment.answer();
                    }
                });
                Thread t2 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        covidDepartment.question();
                    }
                });

                t1.start();
                t2.start();

                t1.join();                                                                              //program nie przejdzie dalej dopoki, nie skoncza sie watki t1 i t2
                t2.join();

                days++;
            }
             catch (InterruptedException e) {
                e.printStackTrace();
                 break;
            }finally {
                System.out.println("System airports wylaczony \n---------------------------------------------\n\n\n\n\n\n");
            }

            FlightCenter.restartFlightNumber();                                                         //metoda odpowiedzialna za zresetowanie numerow lotow (ustawia pole flightnumber na 0)
        }
        FujitsuHospitalDepartment.checking();                                                           //sprawdza ilosc osob z covid, ktore korzystaly z lotnisk Fujitsu

    }


    public static void loadingScreen() {
        for (int i = 0; i<3 ; i++) {
            System.out.print(".");
            try {
                Thread.sleep(500);
                System.out.print(".");
                Thread.sleep(500);
                System.out.print(".");
                Thread.sleep(500);
                System.out.println();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void runFlightControls() {

                Thread t1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        BerlinFlightControl.consumer();
                    }
                });
                t1.start();

                Thread t2 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        LondonFlightControl.consumer();
                    }
                });
                t2.start();

                Thread t3 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ParisFlightControl.consumer();
                    }
                });
                t3.start();

                Thread t4 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        WarsawFlightControl.consumer();
                    }
                });
                t4.start();
    }

    public synchronized static void open(){
        openLatch.countDown();
    }

    public synchronized static void close(){
        closeLatch.countDown();
    }

    public static void setLockdown(boolean lockdown) {
        app.lockdown = lockdown;
    }


}
