package com.solution.concurrent;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockThreadCycle implements Runnable {

    private static final Lock lock = new ReentrantLock();

    private static final String[] ALPHABET = {"a", "b", "c"};

    private static final int MAX_COUNT = ALPHABET.length * 10;

    private String name;

    private static volatile int count = 0;

    public LockThreadCycle(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (count < MAX_COUNT) {
            try {
                lock.lock();
                while (this.name.equals(ALPHABET[count % ALPHABET.length])
                        && count < MAX_COUNT) {  // 双重验证
                    System.out.print(this.name);
                    if (this.name.equals(ALPHABET[ALPHABET.length - 1])) {
                        System.out.println();
                    }
                    count++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

    }

    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                3, 3, 20, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>());

        for (String item : ALPHABET) {
            executor.execute(new LockThreadCycle(item));
        }
        executor.shutdown();
    }
}
