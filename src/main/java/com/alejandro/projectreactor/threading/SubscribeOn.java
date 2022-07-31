package com.alejandro.projectreactor.threading;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class SubscribeOn {

    public static void main(String[] args) {

        var flux = Flux.create(fluxSink -> {
                    printThreadName("create");
                    fluxSink.next(1);
                })
                .doOnNext(i -> printThreadName("Next: " + i));

       flux
               .doFirst(() -> printThreadName("Init"))
               .subscribeOn(Schedulers.boundedElastic())
               .doFirst(() -> printThreadName("Continue"))
               .subscribe(v -> printThreadName("sub" + v));
    }

    private static void printThreadName(String msg) {
        System.out.println(msg + "\t\t: Thread: " + Thread.currentThread().getName());
    }
}
