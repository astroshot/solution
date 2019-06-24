package com.solution.concurrent;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SemaphoreThreadCycle implements Runnable {

    private static final Semaphore sema = new Semaphore(1);

    private static final String[] ALPHABET = {"a", "b", "c"};

    private static final int MAX_COUNT = ALPHABET.length * 10;

    private String name;

    private static volatile int count = 0;

    public SemaphoreThreadCycle(String name) {
        this.name = name;
    }

    public void run() {
        while (count < MAX_COUNT) {
            try {
                sema.acquire();
                while (this.name.equals(ALPHABET[count % ALPHABET.length]) && count < MAX_COUNT) {
                    System.out.print(this.name);
                    count++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                sema.release();
            }
        }
    }

    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                3, 3, 20, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>());

        for (String item : ALPHABET) {
            executor.execute(new SemaphoreThreadCycle(item));
        }
        executor.shutdown();
    }
}
