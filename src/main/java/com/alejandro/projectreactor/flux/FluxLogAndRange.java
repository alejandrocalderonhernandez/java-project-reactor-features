package com.alejandro.projectreactor.flux;

import com.alejandro.projectreactor.Utils;
import reactor.core.publisher.Flux;

public class FluxLogAndRange {

    public static void main(String[] args) {
    Flux.range(5, 10)
                .log()
                .map(i -> "range: " + i)
                .subscribe(System.out::println);
    }
}
