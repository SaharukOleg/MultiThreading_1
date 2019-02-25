package task5;

import java.time.LocalTime;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RandomSleepThread {

    public RandomSleepThread(int numberOfThreads) {
        start(numberOfThreads);
    }

    private void start(int numberOfThreads) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(numberOfThreads);
        Runnable runnable = () -> {
            try {
                int random = new Random().nextInt(10) + 1;
                TimeUnit.SECONDS.sleep(random);
                System.out.println("{\n    Random number - " + random);
                System.out.println(
                        "    " + Thread.currentThread().getName() + " time - " + LocalTime.now() + "\n}");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        };
        System.out.println("\nStart time - " + LocalTime.now());
        for (int i = 0; i < numberOfThreads; i++) {
            executorService.execute(runnable);
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {
        }
        System.out.println("End time - " + LocalTime.now());
    }

    public static void main(String[] args) {
        System.out.print("Please write number of threads - ");
        new RandomSleepThread(new Scanner(System.in).nextInt());

    }
}
