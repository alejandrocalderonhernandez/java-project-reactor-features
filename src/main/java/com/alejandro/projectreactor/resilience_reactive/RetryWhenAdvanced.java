package com.alejandro.projectreactor.resilience_reactive;

import com.alejandro.projectreactor.Utils;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

public class RetryWhenAdvanced {

    public static void main(String[] args) {

        orderService(Utils.getFaker().business().creditCardNumber())
                .retryWhen(Retry.from(
                        retrySignalFlux -> retrySignalFlux
                                .doOnNext(retrySignal -> {
                                    System.out.println(retrySignal.totalRetries());
                                    System.out.println(retrySignal.failure().getMessage());
                                })
                                .handle( (retrySignal, synchronousSink) -> {
                                    if (retrySignal.failure().getMessage().equals("500")) {
                                        synchronousSink.next(1);
                                    } else {
                                        synchronousSink.error(retrySignal.failure());
                                    }
                                })
                                .delayElements(Duration.ofSeconds(1))
                ))
                        .subscribe(Utils.subscriber());
    }

    private static Mono<String> orderService(String ccNumber) {
            return Mono.fromSupplier(() -> {
                processPayment(ccNumber);
                return Utils.getFaker().idNumber().valid();
            });
    }

    private static void  processPayment(String ccNumber) {
        int random = Utils.getFaker().random().nextInt(1, 10);

        if (random < 5) {
            throw new RuntimeException("500"); // Http status 500
        }
        else  if (random > 5) {
            throw new RuntimeException("404"); // Http status 404
        }
    }
}
