package com.alejandro.projectreactor.combining_opetators;

import com.alejandro.projectreactor.Utils;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class Helper {

    public static class NameGenerator {

        List<String> cache = new ArrayList();
        public Flux<String> generateName() {
            return Flux.generate(stringSynchronousSink -> {
                System.out.println("Generated refresh");
                Utils.sleepInSeconds(1L);
                String name = Utils.getFaker().name().firstName();
                cache.add(name);
                stringSynchronousSink.next(name);
            })
                    .cast(String.class)
                    .startWith(this.getFromCache());  // emit every in the flux getFromCache
        }

        private Flux<String> getFromCache() {
            return  Flux.fromIterable(cache);
        }
    }
}
