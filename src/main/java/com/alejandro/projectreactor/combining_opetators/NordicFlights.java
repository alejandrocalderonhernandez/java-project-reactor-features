package com.alejandro.projectreactor.combining_opetators;

import com.alejandro.projectreactor.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class NordicFlights {


    public static Flux<String> getIcelandFlights() {
        return  Flux.range(1, Utils.getFaker().random().nextInt(1, 5))
                .delayElements(Duration.ofSeconds(1))
                .map(i -> "Iceland" + Utils.getFaker().random().nextInt(100, 999))
                .filter(i -> Utils.getFaker().random().nextBoolean());
    }

    public static Flux<String> getNorwayFlights() {
        return  Flux.range(1, Utils.getFaker().random().nextInt(1, 5))
                .delayElements(Duration.ofSeconds(1))
                .map(i -> "Norway" + Utils.getFaker().random().nextInt(100, 999))
                .filter(i -> Utils.getFaker().random().nextBoolean());
    }

    public static Flux<String> getSwedenFlights() {
        return  Flux.range(1, Utils.getFaker().random().nextInt(1, 5))
                .delayElements(Duration.ofSeconds(1))
                .map(i -> "Sweden" + Utils.getFaker().random().nextInt(100, 999))
                .filter(i -> Utils.getFaker().random().nextBoolean());
    }
}
