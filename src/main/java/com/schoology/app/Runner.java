package com.schoology.app;

import java.util.Random;

/**
 * Created by User on 23.06.2016.
 */
public class Runner {

    private SemaphoreImplementation semaphore;
    private Random random = new Random();
    private static final int AVAILABLE_PERMITS = 5;
    public static final int THREADS = 4;

    public static void main(String[] args) {
        new Runner().test();
    }

    public void test(){

        semaphore = new SemaphoreImplementation(AVAILABLE_PERMITS);
        int counter = 0;

        while (counter < THREADS) {
            new Thread(new Worker()).start();
            counter++;
        }
    }

    public class Worker implements Runnable {

        @Override
        public void run() {

            try {

                int permits = random.nextInt(AVAILABLE_PERMITS);

                System.out.println("Thread " + Thread.currentThread().getName() + " start waiting");
                Thread.sleep(1000);
                semaphore.acquire(permits);

                System.out.println("Thread " + Thread.currentThread().getName() + " stop waiting");
                Thread.sleep(1000);
                semaphore.release(permits);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }
    }

}
