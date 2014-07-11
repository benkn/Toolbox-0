package ben.kn.toolbox.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService eService = Executors.newFixedThreadPool(5);

        String formattedName = "Thread-";
        int maxThreads = 10;

        System.out.println("Starting");

        for ( int i = 0; i < maxThreads; i++ ) {
            MyRunnable runnable = new MyRunnable();
            runnable.initialize(formattedName + i, 50000);
            eService.submit(runnable);
        }

        System.out.println("Ending");
    }
}