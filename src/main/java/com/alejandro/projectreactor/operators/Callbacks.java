package com.alejandro.projectreactor.operators;

import com.alejandro.projectreactor.Utils;
import reactor.core.publisher.Flux;

import java.util.stream.IntStream;

public class Callbacks {

    public static void main(String[] args) {
        Flux.create(sink -> {
            System.out.println("Inside create");
            IntStream.range(0,5).forEach(sink::next);
            sink.complete();
            System.out.println("Complete");
        })
        .doOnComplete(() -> System.out.println("doOnComplete"))
        .doFirst(() -> System.out.println("doFirst"))
        .doOnNext(o  -> System.out.println("doOnNext" + o))
        .doOnSubscribe(s -> System.out.println("doOnSubscribe" + s))
        .doOnRequest(request -> System.out.println("doOnRequest" + request))
        .doOnError(err -> System.out.println("doOnError" + err))
        .doOnCancel(() -> System.out.println("doOnCancel"))
        .doOnTerminate(() -> System.out.println("doOnTerminate"))
        .doFinally(signal -> System.out.println("doFinally" + signal))
        .doOnDiscard(Object.class, o  -> System.out.println("doOnDiscard" + o )) //The elements was discarded
        .subscribe(Utils.subscriber());
    }
}
