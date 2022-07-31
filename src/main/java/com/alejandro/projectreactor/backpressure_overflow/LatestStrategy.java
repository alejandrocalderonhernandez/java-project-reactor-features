package com.alejandro.projectreactor.backpressure_overflow;

import com.alejandro.projectreactor.Utils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class LatestStrategy {

    public static void main(String[] args) {
        System.setProperty("reactor.bufferSize.small", "16");
        Flux.create(fluxSink -> {
                    IntStream.range(1, 501).forEach(i -> {
                        fluxSink.next(i);
                        System.out.println("Pushed:  " + i);
                        Utils.sleepInMillis(1L);
                    });
                    fluxSink.complete();
                })
                .onBackpressureLatest()
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> {
                    Utils.sleepInMillis(10L);
                })
                .subscribe(Utils.subscriber());

        Utils.sleepInSeconds(10L);
    }
}
