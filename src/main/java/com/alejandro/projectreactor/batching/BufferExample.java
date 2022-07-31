package com.alejandro.projectreactor.batching;

import com.alejandro.projectreactor.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class BufferExample {

    public static void main(String[] args) {
        eventStream()
                .buffer(5)
                //.bufferTimeout(5, Duration.ofSeconds(2))
                .subscribe(Utils.subscriber());
        Utils.sleepInSeconds(60L);
    }

    private static Flux<String> eventStream() {
        return  Flux.interval(Duration.ofMillis(300))
                .map(i -> "Event: " + i);
    }

}
