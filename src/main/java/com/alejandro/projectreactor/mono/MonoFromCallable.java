package com.alejandro.projectreactor.mono;

import com.alejandro.projectreactor.Utils;
import reactor.core.publisher.Mono;

import java.util.concurrent.Callable;

public class MonoFromCallable {

    public static void main(String[] args) {

        Callable<String> nameWithDelay = MonoFromCallable::getName;

        Mono.fromCallable(nameWithDelay).subscribe(System.out::println);
    }


    private  static String getName() throws InterruptedException {
        System.out.println("Generating name...");
        Thread.sleep(400);
        return Utils.getFaker().name().firstName();
    }
}
