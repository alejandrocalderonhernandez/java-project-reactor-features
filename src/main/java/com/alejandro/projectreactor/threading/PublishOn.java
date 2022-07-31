package com.alejandro.projectreactor.threading;

import com.alejandro.projectreactor.Utils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.stream.IntStream;

public class PublishOn {

    public static void main(String[] args) {

        var flux = Flux.create(fluxSink -> {
                    printThreadName("create");
                    IntStream.range(1, 20).forEach(i -> {
                        fluxSink.next(i);
                        Utils.sleepInSeconds(1L);
                    });
                    fluxSink.complete();

                })
                .doOnNext(i -> printThreadName("Next: " + i));

        flux
                .subscribeOn(Schedulers.boundedElastic())
                .doOnNext(i -> printThreadName("Next: " + i))
                .publishOn(Schedulers.parallel())
                .subscribe(v -> printThreadName("sub" + v));

        Utils.sleepInSeconds(5L);
    }

    private static void printThreadName(String msg) {
        System.out.println(msg + "\t\t: Thread: " + Thread.currentThread().getName());
    }
}
