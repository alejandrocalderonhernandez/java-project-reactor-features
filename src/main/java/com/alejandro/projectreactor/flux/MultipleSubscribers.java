package com.alejandro.projectreactor.flux;

import com.alejandro.projectreactor.Utils;
import reactor.core.publisher.Flux;

public class MultipleSubscribers {

    public static void main(String[] args) {
        var myFlux = Flux.just(1,2,3);
        myFlux.subscribe(
                Utils.onNext()
        );

        myFlux.subscribe(
                Utils.onNext()
        );

        myFlux.subscribe(
                Utils.onNext()
        );


        var newFlux = myFlux.map(n -> "toString " + n);

        newFlux.subscribe(
                Utils.onNext()
        );


    }
}
