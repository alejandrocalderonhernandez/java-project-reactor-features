package com.alejandro.projectreactor.mono;

import reactor.core.publisher.Mono;
import static com.alejandro.projectreactor.Utils.*;
public class MonoEmptyOnError {

    public static void main(String[] args) {
            userRepository(1).subscribe(
                    onNext(),
                    onError(),
                    onComplete()
            );

        userRepository(2).subscribe(
                onNext(),
                onError(),
                onComplete()
        );

        userRepository(3).subscribe(
                onNext(),
                onError(),
                onComplete()
        );
    }

    private static Mono<String> userRepository(int userId) {
        switch (userId) {
            case 1: return Mono.just(getFaker().name().firstName());
            case 2: return Mono.empty();
            default: return Mono.error(new RuntimeException("Not allowed"));
        }
    }
}
