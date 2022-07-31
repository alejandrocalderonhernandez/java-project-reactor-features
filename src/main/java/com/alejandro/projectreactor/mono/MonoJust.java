package com.alejandro.projectreactor.mono;

import reactor.core.publisher.Mono;

public class MonoJust {
    public static void main(String[] args) {
        var mono = Mono.just(1);

        mono.subscribe(System.out::println);
    }
}
