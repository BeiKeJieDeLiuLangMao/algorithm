package bbm;

import java.util.HashMap;

/**
 * @author bbm
 * @date 2020/7/15
 */
public class HashMapLoopTest {

    public static void main(String[] args) throws InterruptedException {
        int count = 0;
        while (true) {
            System.out.println("Begin epoch: " + count);
            HashMap<Integer, Integer> map = new HashMap<>();
            Thread t1 = new Thread(() -> {
                for (int i = 0; i < 50000; i++) {
                    //System.out.println("Thread1: " + i);
                    map.put(i, i);
                }
            });
            Thread t2 = new Thread(() -> {
                for (int i = 50000; i >= 0; i--) {
                    //System.out.println("Thread2: " + i);
                    map.put(i, i);
                }
            });
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            System.out.println("Finish epoch: " + count++);
        }
    }
}
