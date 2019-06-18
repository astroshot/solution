package com.solution.concurrent;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CASThreadCycle {

    public static class ThreadPrinter implements Runnable {

        private final static String[] ALPHABET = {"a", "b", "c"};

        private static AtomicInteger index = new AtomicInteger(0);

        private String name;

        public ThreadPrinter(String name) {
            this.name = name;
        }

        public void run() {
            while (index.get() < 30) {
                if (this.name.equals(getNext())) {
                    System.out.print(this.name);
                    index.getAndIncrement();
                }
            }
        }

        public static String getNext() {
            return ALPHABET[index.get() % ALPHABET.length];
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                3, 3, 20, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>());

        for (String item : ThreadPrinter.ALPHABET) {
            executor.execute(new ThreadPrinter(item));
        }

        executor.shutdown();
    }
}
