package com.alejandro.projectreactor.threading;

import java.util.stream.IntStream;

public class App {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        IntStream range = IntStream.range(0, 1000000);

        range.forEach(i -> {
            Thread thread = new Thread(() -> {
                System.out.println("Job number: " + i);
            });
            thread.start();
        });

        long finish = System.currentTimeMillis();
        long totalTime = finish - start;

        System.out.println("Finish in: " + (totalTime / 1000));
    }
}
