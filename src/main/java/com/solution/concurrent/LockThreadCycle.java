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

    public LockThreadCycle(String name) {
        this.name = name;
    }

    private static class IntegerCounter {
        private volatile int count;

        public IntegerCounter(int count) {
            this.count = count;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }

    private static final IntegerCounter counter = new IntegerCounter(0);

    @Override
    public void run() {
        while (counter.getCount() < MAX_COUNT) {
            try {
                lock.lock();
                while (this.name.equals(ALPHABET[counter.getCount() % ALPHABET.length])
                        && counter.getCount() < MAX_COUNT) {  // 双重验证
                    System.out.print(this.name);
                    if (this.name.equals(ALPHABET[ALPHABET.length - 1])) {
                        System.out.println();
                    }
                    counter.setCount(counter.getCount() + 1);
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
