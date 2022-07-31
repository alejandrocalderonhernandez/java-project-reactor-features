package com.alejandro.projectreactor.mono;

import com.alejandro.projectreactor.Utils;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public class MonoFromFuture {

    public static void main(String[] args) {
        Mono.fromFuture(getName()).subscribe(Utils.onNext());
        Utils.sleepInSeconds(1L);
    }

    private  static CompletableFuture<String> getName() {
        return CompletableFuture.supplyAsync(() -> Utils.getFaker().name().fullName());
    }
}
