package com.alejandro.projectreactor.operators;

import com.alejandro.projectreactor.Utils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Random;

public class OnError {

    public static void main(String[] args) {
        Flux.range(1, 10)
                .log()
                .map(i -> 10 / (5 - i))
                //.onErrorResume(e -> hansonFallback(e))
                //.onErrorReturn(-1)
                .onErrorContinue((err, obj) -> {

                })
                .subscribe(Utils.subscriber());
    }

    public  static Mono<Integer> hansonFallback(Throwable error) {
        System.out.println(error.getMessage());
        return Mono.fromSupplier(() -> Utils.getFaker().random().nextInt(100, 200));
    }
}
