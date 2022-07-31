package com.alejandro.projectreactor.resilience_reactive;

import com.alejandro.projectreactor.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Retry {

    private static final AtomicInteger intRef = new AtomicInteger(0);
    public static void main(String[] args) {
        getInts()
                .retry(3) //Retry max attempts
                .retryWhen(reactor.util.retry.Retry.fixedDelay(2, Duration.ofMillis(500))) // Retry after time given
                .subscribe(Utils.subscriber());
    }

    private static Flux<Integer> getInts() {
        return Flux.range(1, 3)
                .doOnSubscribe(s -> System.out.println("Subscribes: " + s))
                .doOnComplete(() -> System.out.println("Finish"))
                .map(i -> i/0)
                .doOnError(System.err::println);
    }

}
