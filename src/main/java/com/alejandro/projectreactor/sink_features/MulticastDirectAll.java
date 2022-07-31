package com.alejandro.projectreactor.sink_features;

import com.alejandro.projectreactor.Utils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;

public class MulticastDirectAll {

    public static void main(String[] args) {
        System.setProperty("reactor.bufferSize.small", "16");
        Sinks.Many<Object> mySyncMany= Sinks.many().multicast().directBestEffort();

        Flux<Object> myFlux = mySyncMany.asFlux();

        myFlux.subscribe(Utils.subscriber("Sax "));
        myFlux.delayElements(Duration.ofMillis(200))
                .subscribe(Utils.subscriber("Mike"));

        for (int i = 0; i<100; i++) {
            mySyncMany.tryEmitNext(i);
        }

        Utils.sleepInSeconds(10L);

    }
}
