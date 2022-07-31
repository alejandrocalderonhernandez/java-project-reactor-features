package com.alejandro.projectreactor.operators;

import com.alejandro.projectreactor.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class DefaultIfEmpty {

    public static void main(String[] args) {
        getOrderNumbers()
                .filter(i -> i > 10)
                .defaultIfEmpty(999999999)
                .subscribe(Utils.subscriber());
    }

    private static Flux<Integer> getOrderNumbers() {
        return Flux.range(1, 10);
    }
}
