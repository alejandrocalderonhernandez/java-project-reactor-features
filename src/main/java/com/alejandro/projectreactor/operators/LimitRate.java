package com.alejandro.projectreactor.operators;

import com.alejandro.projectreactor.Utils;
import reactor.core.publisher.Flux;

public class LimitRate {

    public static void main(String[] args) {
        Flux.range(1, 1000)
                .log()
                .limitRate(100, 99)
                .subscribe(Utils.subscriber());
    }
}
