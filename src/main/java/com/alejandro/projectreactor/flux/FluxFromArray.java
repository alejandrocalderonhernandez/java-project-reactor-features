package com.alejandro.projectreactor.flux;

import com.alejandro.projectreactor.Utils;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.HashSet;

public class FluxFromArray {

    public static void main(String[] args) {
        var array = Arrays.asList("a", "b", "c");

        var myFlux = Flux.fromIterable(array);

        myFlux.subscribe(Utils.onNext());

        HashSet<String> set = new HashSet<>();
        set.add("N");

        myFlux = Flux.fromIterable(set);
        myFlux.subscribe(Utils.onNext());

    }
}
