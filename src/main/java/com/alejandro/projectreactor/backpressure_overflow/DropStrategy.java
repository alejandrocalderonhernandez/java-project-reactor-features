package com.alejandro.projectreactor.backpressure_overflow;

import com.alejandro.projectreactor.Utils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import reactor.util.concurrent.Queues;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class DropStrategy {

    /*public static final int SMALL_BUFFER_SIZE = Math.max(16, Integer.parseInt(System.getProperty("reactor.bufferSize.small", "256")));
    * In subscriber only emitted 256 values for above
     */
    public static void main(String[] args) {

        var list = new ArrayList<Object>();
        System.setProperty("reactor.bufferSize.small", "16");
        Flux.create(fluxSink -> {
                    IntStream.range(1, 501).forEach(i -> {
                        fluxSink.next(i);
                        System.out.println("Pushed:  " + i);
                        Utils.sleepInMillis(1L);
                    });
                    fluxSink.complete();
                })
                //.onBackpressureDrop()
                .onBackpressureDrop(list::add)
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> {
                    Utils.sleepInMillis(10L);
                })
                .subscribe(Utils.subscriber());

        Utils.sleepInSeconds(10L);
        list.forEach(System.out::println);  // print the values dropped
    }
}
