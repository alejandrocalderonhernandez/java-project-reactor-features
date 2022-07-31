package com.alejandro.projectreactor.mono;

import com.alejandro.projectreactor.Utils;
import reactor.core.publisher.Mono;

public class MonoFromRunnable {

    public static void main(String[] args) {

        Mono.fromRunnable(() -> System.out.println("From runnable"))
                .subscribe(
                        Utils.onNext(),
                        Utils.onError(),
                        Utils.onComplete()
                );
    }


}
