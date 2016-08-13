package com.schoology.app;

/**
 * Created by User on 23.06.2016.
 */
public class SemaphoreImplementation implements Semaphore{

    private volatile int availablePermits = 0;
    private final Object lock = new Object();

    public SemaphoreImplementation(int availablePermits) {
        this.availablePermits = availablePermits;
    }

    @Override
    public void acquire() throws InterruptedException {

        synchronized (lock) {

            if (getAvailablePermits() > 0) {

                availablePermits--;
            } else {

                lock.wait();
            }
        }
    }

    @Override
    public void acquire(int permits) throws InterruptedException {

        synchronized (lock){

            if (getAvailablePermits() >= permits){

                availablePermits -= permits;
                System.out.println("Available permits:" + availablePermits);
            } else {

                lock.wait();
            }
        }
    }

    @Override
    public void release() {

        synchronized (lock){

            availablePermits++;
            lock.notify();
        }
    }

    @Override
    public void release(int permits) {

        synchronized (lock){

            while (permits >= 0) {

                availablePermits++;
                permits--;
                lock.notify();
            }
            System.out.println("Available permits:" + availablePermits);
        }
    }

    @Override
    public int getAvailablePermits() {

        return availablePermits;
    }
}
