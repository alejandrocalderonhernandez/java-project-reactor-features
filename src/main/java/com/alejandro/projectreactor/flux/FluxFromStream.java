package com.alejandro.projectreactor.flux;

import com.alejandro.projectreactor.Utils;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.stream.Stream;

public class FluxFromStream {

    public static void main(String[] args) {
        var stream = Arrays.asList(1,2,3).stream();

        //var myFlux = Flux.fromStream(stream);
        var myFlux = Flux.fromStream(() -> stream) ;


        myFlux.subscribe(Utils.onNext());
        myFlux.subscribe(
                Utils.onNext(),
                Utils.onError()
        );
    }
}
