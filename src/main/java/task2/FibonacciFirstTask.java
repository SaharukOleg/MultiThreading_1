package task2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class FibonacciFirstTask {
    private static final List<Long> list = Collections.synchronizedList(new ArrayList<>());
    private static volatile int size;


    public FibonacciFirstTask(int sizeOfSequence) {
        size = sizeOfSequence - 1;
        list.add((long) 1);
    }

    public void start() {
        createThreads(size);
        while (list.size() != size + 1) {

        }
        System.out.print("Fibonacci sequence:\n{ ");
        list.forEach((a) -> System.out.print(a + ", "));
        System.out.print("}");
    }

    private void createThreads(int numberOfThreads) {
        for (int i = 0; i < numberOfThreads; i++) {
            new Thread(this::calculateFibonacciSequence, "Thread - " + i).start();
        }
    }

    private void calculateFibonacciSequence() {
        synchronized (list) {
            System.out.println(Thread.currentThread().getName());
            long temp = list.get(list.size() - 1);
            if (list.size() == 1) {
                list.add(temp);
            } else {
                temp = list.get(list.size() - 1);
                temp += list.get(list.size() - 2);
                list.add(temp);
            }
        }
    }

    public static void main(String[] args) {
        System.out.print("Please write number of size fibonacci sequence:\nsize - ");
        new FibonacciFirstTask(new Scanner(System.in).nextInt()).start();
    }
}
