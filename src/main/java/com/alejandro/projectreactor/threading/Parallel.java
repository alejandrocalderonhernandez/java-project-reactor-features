package com.alejandro.projectreactor.threading;

import com.alejandro.projectreactor.Utils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.stream.IntStream;

public class Parallel {

    public static void main(String[] args) {

        Flux.range(1, 10)
                .parallel(5)
                .runOn(Schedulers.parallel())
                .doOnNext(i -> printThreadName("Next second: " + i))
                .subscribe(v -> printThreadName("sub" + v));

        Utils.sleepInSeconds(5L);
    }

    private static void printThreadName(String msg) {
        System.out.println(msg + "Thread: " + Thread.currentThread().getName());
    }
}
