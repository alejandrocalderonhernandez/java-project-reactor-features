package com.alejandro.projectreactor.batching;

import com.alejandro.projectreactor.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class GroupByExample {

    public static void main(String[] args) {

        Flux.range(1, 30)
                .delayElements(Duration.ofMillis(300))
                .groupBy(i -> i % 2)
                .subscribe(g -> process(g, g.key()));

        Utils.sleepInSeconds(10L);

    }

    private static  void process(Flux<Integer> flux, Integer key) {
        flux.subscribe(i -> System.out.println("Key: " + key + " Value: " + i));
    }
}
