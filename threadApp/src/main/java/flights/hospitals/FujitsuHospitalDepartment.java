//klasa sprawdzajaca ilosc osob z covid, ktore korzystaly z lotnisk Fujitsu - uzyto: Future, ExecutorService

package flights.hospitals;

import flights.app;

import java.util.Scanner;
import java.util.concurrent.*;

/**
 * Created by Kamil on 01.10.2020.
 */
public class FujitsuHospitalDepartment {

    public static void checking(){

        ExecutorService executor = Executors.newCachedThreadPool();

        Future<Integer> future = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("Sprawdzanie ilosci osob, ktore maja stwierdzonego covida i korzystaly z lotnisk Fujitsu");
                int sickPeoples;

                Thread t1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        TestingDepartment.producer();

                    }
                });
                Thread t2 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        TestingDepartment.consumer();
                    }
                });

                t1.start();
                t2.start();

                try {
                    Thread.sleep(6000);
                    TestingDepartment.status=false;                                                                     //zmiana pola status (przerwanie 2 petli while w klasie TestingDepartment)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                sickPeoples = TestingDepartment.getQueue().size();

                return sickPeoples;
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Aby otrzymac wynik wcisnij 1 " +
                        "(obliczenia moga jeszcze chwile potrwac), jesli chcesz anulowac sprawdzanie podaj dowolny znak/ciag znakow");

                Scanner scanner = new Scanner(System.in);
                String answer = null;
                answer=scanner.nextLine();

                if(answer.equals("1")){
                    System.out.println("Nie anulowano");
                    try {
                        app.loadingScreen();
                        Thread.sleep(4000);
                        System.out.println("Ilosc osob u ktorych stwierdzono covid - " + future.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }else{
                    System.out.println("Anulowano");
                    future.cancel(true);
                }
            }
        });
        t3.start();

        executor.shutdown();
        try {
            executor.awaitTermination(1,TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
