package com.alejandro.projectreactor.backpressure_overflow;

import com.alejandro.projectreactor.Utils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Window {

    private static AtomicInteger count = new AtomicInteger(1);

    public static void main(String[] args) {
        eventStream()
                .window(5)
                .flatMap(flux -> saveEvents(flux))
                .subscribe(Utils.subscriber());
        Utils.sleepInSeconds(60L);
    }

    private static Flux<String> eventStream() {
        return  Flux.interval(Duration.ofMillis(500))
                .map(i -> "Event: " + i);
    }

    private static Mono<Integer> saveEvents(Flux<String> flux) {
        return flux
                .doOnNext(e -> System.out.println("Saving + " + e))
                .doOnComplete(() -> System.out.println("Saving batch"))
                .then(Mono.just(count.getAndIncrement()));
    }

}
