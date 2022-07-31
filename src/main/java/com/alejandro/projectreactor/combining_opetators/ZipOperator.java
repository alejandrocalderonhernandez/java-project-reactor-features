package com.alejandro.projectreactor.combining_opetators;

import com.alejandro.projectreactor.Utils;
import reactor.core.publisher.Flux;

public class ZipOperator {

    public static void main(String[] args) {

        Flux.zip(
                getChassis(),
                getEngine(),
                getTires()
        ).subscribe(Utils.subscriber());
    }

    private  static Flux<String> getChassis() {
        return Flux.range(1, 9)
                .map(i -> "Chassis part no: " + i);
    }
    private  static Flux<String> getEngine() {
        return Flux.range(1, 7)
                .map(i -> "Engine part no: " + i);
    }
    private  static Flux<String> getTires() {
        return Flux.range(1, 4)
                .map(i -> "Tires unit no: " + i);
    }
}
