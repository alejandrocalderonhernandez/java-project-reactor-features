package com.alejandro.projectreactor.backpressure_overflow;

import com.alejandro.projectreactor.Utils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.stream.IntStream;

public class OverflowDemo {

    public static void main(String[] args) {
        Flux.create(fluxSink -> {
            IntStream.range(1, 501).forEach(i -> {
                fluxSink.next(i);
                System.out.println("Pushed:  " + i);
            });
            fluxSink.complete();
        })
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> {
                    Utils.sleepInMillis(10L);
                })
                .subscribe(Utils.subscriber());

        Utils.sleepInSeconds(60L);
    }
}
