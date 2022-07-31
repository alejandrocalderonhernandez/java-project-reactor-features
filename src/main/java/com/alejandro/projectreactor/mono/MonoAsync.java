package com.alejandro.projectreactor.mono;

import com.alejandro.projectreactor.Utils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class MonoAsync {

    public static void main(String[] args) {
        getName();
        var nameResponse = getName()
                .subscribeOn(Schedulers.boundedElastic())
                .block();
        System.out.println(nameResponse);
        getName();
    }
    private  static Mono<String> getName() {
        System.out.println("Start job...");
        return Mono.fromSupplier(() -> {
            System.out.println("Generating name...");
            Utils.sleepInSeconds(3L);
            return Utils.getFaker().name().firstName();
        }).map(String::toUpperCase);
    }
}
