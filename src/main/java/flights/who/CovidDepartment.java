//Klasa informujaca o ilosci zakazonych w Europie, pozwala zamknac lotniska (przerwac petle while w metodzie main klasy
//app za pomoca metody app.setLockdown(true)) // Uzyto: wait() and notify()

package flights.who;

import flights.app;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by Kamil on 25.09.2020.
 */
public class CovidDepartment {

    public void answer(){
        synchronized (this) {
            System.out.println("Ilosc zakazonych osob w Europie " + getNumberOfInfected());
            try {
                wait();                                                                                             //aby oddac watek metodzie question()
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void question(){
        try {
            Thread.sleep(2000);                                                                               //aby oddac watek metodzie answer()
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (this){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Zamykamy lotniska?");
            String check=null;

            while(true){
                    check = scanner.next();
                    check = check.toLowerCase();
                if("tak".equals(check)){
                    app.setLockdown(true);
                    System.out.println("Lotniska zostaly zamkniete \n");
                    break;
                }else if ("nie".equals(check)){
                    //app.setLockdown(false);
                    System.out.println("Lotniska pozostaja otwarte \n");
                    break;
                }else {
                    System.out.println("Podales zla odpowiedz, poprawne odpowiedzi: tak / nie");
                }
                }
            notify();                                                                                               //obudzi metode answer tej klasy
        }
    }

    private int getNumberOfInfected(){
        int numberOfInfected;
        Random random = new Random();
        numberOfInfected = random.nextInt(20000)+10000;
        return numberOfInfected;
    }
}
