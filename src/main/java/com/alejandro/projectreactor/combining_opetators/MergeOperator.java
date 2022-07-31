package com.alejandro.projectreactor.combining_opetators;

import com.alejandro.projectreactor.Utils;
import reactor.core.publisher.Flux;

public class MergeOperator {

    public static void main(String[] args) {
        Flux.merge(
                NordicFlights.getIcelandFlights(),
                NordicFlights.getSwedenFlights(),
                NordicFlights.getNorwayFlights()
        ).subscribe(Utils.subscriber());

        Utils.sleepInSeconds(10L);
    }
}
