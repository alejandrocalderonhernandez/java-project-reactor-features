package com.alejandro.projectreactor.operators;

import com.alejandro.projectreactor.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Delay {

    public static void main(String[] args) {

        System.setProperty("reactor.bufferSize.x", "100");
        Flux.range(1, 100)
                .log()
                .delayElements(Duration.ofSeconds(1))
                .subscribe(Utils.subscriber());
    }
}
