package com.alejandro.projectreactor.mono;

import com.alejandro.projectreactor.Utils;
import reactor.core.publisher.Mono;

public class MonoFromSupplier {

    public static void main(String[] args) {
       Mono.fromSupplier(MonoFromSupplier::getName).subscribe(System.out::println);
    }

    private  static String getName() {
        System.out.println("Generating name...");
        return Utils.getFaker().name().firstName();
    }
}
