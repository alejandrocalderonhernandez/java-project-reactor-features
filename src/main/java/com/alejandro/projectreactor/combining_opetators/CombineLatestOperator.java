package com.alejandro.projectreactor.combining_opetators;

import com.alejandro.projectreactor.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class CombineLatestOperator {

    public static void main(String[] args) {

        Flux.combineLatest(getLetterUpper(), getLetterLower(), (u, l) -> u + " - " + l)
                .subscribe(Utils.subscriber());

        Utils.sleepInSeconds(20L);
    }

    private  static Flux<String> getLetterUpper() {
        return  Flux.just("A", "B", "C", "D")
                .delayElements(Duration.ofSeconds(1));
    }

    private  static Flux<String> getLetterLower() {
        return  Flux.just("a", "b", "c", "d")
                .delayElements(Duration.ofSeconds(3));
    }
}
