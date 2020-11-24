package com.solution.concurrent;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SyncThreadCycle implements Runnable {

    private static final String[] ALPHABET = {"a", "b", "c"};

    private static final int MAX_COUNT = ALPHABET.length * 10;

    private String name;

    public SyncThreadCycle(String name) {
        this.name = name;
    }

    private static class ThreadSync {

        private volatile int index;

        public ThreadSync(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }

    // synchronized object must be the same
    private static final ThreadSync sync = new ThreadSync(0);

    public void run() {
        while (sync.getIndex() < MAX_COUNT) {
            synchronized (sync) {
                if (sync.getIndex() < MAX_COUNT) {  // 双重验证
                    if (ALPHABET[sync.getIndex() % ALPHABET.length].equals(this.name)) {
                        System.out.print(this.name);
                        sync.setIndex(sync.getIndex() + 1);
                        sync.notifyAll();
                    } else {
                        try {
                            sync.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                3, 3, 20, TimeUnit.MINUTES, new LinkedBlockingQueue<>());

        for (String item : ALPHABET) {
            executor.execute(new SyncThreadCycle(item));
        }
        executor.shutdown();
    }
}
