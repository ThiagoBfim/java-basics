package com.bomfim.threads;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Threads {

    public static void main(String[] args) throws InterruptedException {

        concurrencyProblem();
        concurrencyProblemFix();
        concurrencyProblemMap();
        concurrencyProblemFixMap();

    }

    private static void concurrencyProblemFixMap() throws InterruptedException {
        final Map<Integer, Integer> map = new ConcurrentHashMap<>();

        Runnable runner1 = () -> {
            for (int i = 0; i < 1_000; i++) {
                map.put(i, i);
            }
        };

        Runnable runner2 = () -> {
            for (Entry<Integer, Integer> item : map.entrySet()) {
                map.remove(item.getKey());
            }
        };
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        executorService.execute(runner1);
        executorService.execute(runner2);
        Thread.sleep(1000);
        executorService.shutdown();

        System.out.println(map.size());
    }

    private static void concurrencyProblemMap() throws InterruptedException {

        final Map<Integer, Integer> map = new HashMap<>();

        Runnable runner1 = () -> {
            for (int i = 0; i < 1_000; i++) {
                map.put(i, i);
            }
        };

        Runnable runner2 = () -> {
            try {
                for (Entry<Integer, Integer> item : map.entrySet()) {
                    map.remove(item.getKey());
                }
            } catch (ConcurrentModificationException exception) {
                System.out.println(exception);
            }
        };
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        executorService.execute(runner1);
        executorService.execute(runner2);
        Thread.sleep(1000);
        executorService.shutdown();
        System.out.println(map.size());


    }

    private static void concurrencyProblem() throws InterruptedException {

        var counter = new Counter();
        Runnable runner1 = () -> {
            for (int i = 0; i < 10_000; i++) {
                counter.increment();
            }
        };
        Runnable runner2 = () -> {
            for (int i = 0; i < 10_000; i++) {
                counter.increment();

            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(100);

        executorService.execute(runner1);
        executorService.execute(runner2);
        Thread.sleep(500);
        executorService.shutdown();
        System.out.println(counter.value);  // ??
    }

    private static void concurrencyProblemFix() throws InterruptedException {

        var counter = new Counter();
        Runnable runner1 = () -> {
            for (int i = 0; i < 10_000; i++) {
                counter.incrementSynchronized();
            }
        };
        Runnable runner2 = () -> {
            for (int i = 0; i < 10_000; i++) {
                counter.incrementSynchronized();

            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(100);

        executorService.execute(runner1);
        executorService.execute(runner2);
        Thread.sleep(500);
        executorService.shutdown();
        System.out.println(counter.value);  // 20_000
    }


    private static class Counter {

        int value = 0;

        public void increment() {
            value++;
        }

        public synchronized void incrementSynchronized() {
            value++;
        }

    }

}
