package com.alejandro.projectreactor.sink_features;

import com.alejandro.projectreactor.Utils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

public class SinkFundamentals {

    public static void main(String[] args) {

        // Mono -> value / empty / error
        Sinks.One<Object> mySyncOne = Sinks.one();

        //Sinks.Many<Object> mySyncMany= Sinks.many().unicast().onBackpressureBuffer();
        Sinks.Many<Object> mySyncMany= Sinks.many().multicast().onBackpressureBuffer();

        Mono<Object> myMono = mySyncOne.asMono();

        Flux<Object> myFlux = mySyncMany.asFlux();

        myMono.subscribe(Utils.subscriber("Sax "));
        myMono.subscribe(Utils.subscriber("Sax2 "));

        myFlux.subscribe(Utils.subscriber("Neto"));
        myFlux.subscribe(Utils.subscriber("Neto2"));
        mySyncOne.tryEmitValue("Hi there");
        mySyncOne.tryEmitValue("Hi there x2");

        mySyncMany.tryEmitNext("Hi there");
        mySyncMany.tryEmitNext("Hi there x2");

        //mySyncMany.emitNext("",  (s, e) -> true); multi thread
    }
}
