package com.alejandro.projectreactor.flux;

import com.alejandro.projectreactor.Utils;
import reactor.core.publisher.Flux;

public class FluxIntro {

    public static void main(String[] args) {
        var myFlux = Flux.just(1,2,3);

        myFlux.subscribe(
                Utils.onNext(),
                Utils.onError(),
                Utils.onComplete()
        );
    }
}
