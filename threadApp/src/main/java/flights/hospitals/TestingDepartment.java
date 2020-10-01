//Klasa ktora w sposob "losowy" dodaje osoby zarazone do arrayblockingqueue
//Uzyto: producer consumer ArrayBlockingQueue

package flights.hospitals;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Kamil on 01.10.2020.
 */
public class TestingDepartment {

    private static BlockingQueue<Boolean> queue = new ArrayBlockingQueue<>(10000);

    public static volatile Boolean status=true;

    public static BlockingQueue<Boolean> getQueue() {
        return queue;
    }

    public static void producer(){
        Random random = new Random();

        while(status){
            try {
                queue.put(random.nextBoolean());
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public static void consumer(){
        try{
            Thread.sleep(500);
            while(status){
                if(queue.element()==false) {
                    queue.take();
                }
            }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}
