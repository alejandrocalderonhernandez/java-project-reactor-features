package com.alejandro.projectreactor.resilience_reactive;

import com.alejandro.projectreactor.Utils;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

public class Repeat {

    private static final AtomicInteger intRef = new AtomicInteger(0);

    public static void main(String[] args) {
        getInts()
                //.repeat(2)
                .repeat(() -> intRef.get() < 5)
                .subscribe(Utils.subscriber());
    }

    private static Flux<Integer> getInts() {
        return Flux.range(1, 3)
                .doOnSubscribe(s -> System.out.println("Subscribes: " + s))
                .doOnComplete(() -> System.out.println("Finish"))
                .map(i -> intRef.getAndIncrement());
    }
}
