package com.alejandro.projectreactor.threading;

import com.alejandro.projectreactor.Utils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.stream.IntStream;

public class PublishAndSubscribeOn {

    public static void main(String[] args) {

        var flux = Flux.create(fluxSink -> {
                    printThreadName("create");
                    IntStream.range(1, 5).forEach(i -> {
                        fluxSink.next(i);
                    });
                    fluxSink.complete();

                })
                .doOnNext(i -> printThreadName("Next first: " + i));

        flux
                .publishOn(Schedulers.parallel())
                .doOnNext(i -> printThreadName("Next second: " + i))
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(v -> printThreadName("sub" + v));

        Utils.sleepInSeconds(5L);
    }

    private static void printThreadName(String msg) {
        System.out.println(msg + "Thread: " + Thread.currentThread().getName());
    }


}
