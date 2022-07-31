package com.alejandro.projectreactor.flux;

import com.alejandro.projectreactor.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;

public class CodeChallenge2 {

    public static void main(String[] args) {
        var random = new Random();
        Flux.interval(Duration.ofSeconds(1))
                .take(5)
                .map(second -> random.nextInt(10) + 1)
                .handle((r, sink) -> {
                    if (r < 2) sink.error(new RuntimeException("the bag has fallen"));
                    if (r >= 2 && r < 7) sink.next(r);
                    if(r >= 7) sink.complete();
                })
                .subscribe(
                        r -> System.out.println("In bag: " + r),
                        err -> System.out.println(err.getMessage()),
                        () -> System.out.println("Finish proses"));

        Utils.sleepInSeconds(5L);
    }

}
