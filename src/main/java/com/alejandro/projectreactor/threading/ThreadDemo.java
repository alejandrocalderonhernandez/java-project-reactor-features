package com.alejandro.projectreactor.threading;

import reactor.core.publisher.Flux;

public class ThreadDemo {

    public static void main(String[] args) {

        var flux = Flux.create(fluxSink -> {
            printThreadName("create");
            fluxSink.next(1);
        })
        .doOnNext(i -> printThreadName("Next: " + i));

        Runnable run = () -> flux.subscribe(v -> printThreadName("sub" + v));

        for (var i = 0; i<5; i++) {
            new Thread(run).start();
        }

    }

    private static void printThreadName(String msg) {
        System.out.println(msg + "\t\t: Thread: " + Thread.currentThread().getName());
    }
}
