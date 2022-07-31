package com.alejandro.projectreactor.backpressure_overflow;

import com.alejandro.projectreactor.Utils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.stream.IntStream;

public class ErrorStrategy {

    public static void main(String[] args) {

        System.setProperty("reactor.bufferSize.small", "16");
        Flux.create(fluxSink -> {
                    for (int i = 1; (i< 201 && !fluxSink.isCancelled()); i++) {
                        fluxSink.next(i);
                        System.out.println("Pushed:  " + i);
                        Utils.sleepInMillis(1L);
                    }
                    fluxSink.complete();
                })
                .onBackpressureBuffer(500, o -> System.out.println("Items dropped: " + o)) // Increase the size the buffer for elements received
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> {
                    Utils.sleepInMillis(10L);
                })
                .subscribe(Utils.subscriber());

        Utils.sleepInSeconds(10L);
    }
}
