package com.alejandro.projectreactor.operators;

import com.alejandro.projectreactor.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class TimeOut {

    public static void main(String[] args) {
        getOrderNumbers()
                .timeout(Duration.ofSeconds(2), fallback())
                .subscribe(Utils.subscriber());

        Utils.sleepInSeconds(10L);
    }

    private static  Flux<Integer> getOrderNumbers() {
        return Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(5));
    }

    private static Flux<Integer> fallback() {
        return Flux.range(1,2)
                .delayElements(Duration.ofMillis(100));
    }
}
