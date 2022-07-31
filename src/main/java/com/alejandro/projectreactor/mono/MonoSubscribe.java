package com.alejandro.projectreactor.mono;

import reactor.core.publisher.Mono;

import static  com.alejandro.projectreactor.Utils.onNext;
import static  com.alejandro.projectreactor.Utils.onError;
import static  com.alejandro.projectreactor.Utils.onComplete;

public class MonoSubscribe {

    public static void main(String[] args) {
        var mono = Mono.just("Ball");  //Publisher

        mono.subscribe(); //One way in this case don't happen anything
        mono.subscribe(
                item -> System.out.println(item),  //onNext
                err -> System.out.println(err.getMessage()), //onError
                () -> System.out.println("Task completed") //onComplete
        );

        mono.subscribe(
                onNext(),
                onError(),
                onComplete()
        );
    }
}
