package com.alejandro.projectreactor.operators;

import com.alejandro.projectreactor.Utils;
import reactor.core.publisher.Flux;

import java.util.function.Function;

public class Transform {
    public static void main(String[] args) {
        getPersons()
                .transform(applyFilterMap())
                .subscribe(Utils.subscriber());
    }

    public  static Flux<Person> getPersons() {
        return Flux.range(1, 10)
                .map(i -> new Person());
    }

    public static Function<Flux<Person>, Flux<Person>> applyFilterMap() {
        return f -> f
                .filter(p -> p.getAge() > 2)
                .doOnNext(p -> p.setName(p.getName().toUpperCase()))
                .doOnDiscard(Person.class, p -> System.out.println("Not allowed minor age than: " + p.getAge()));
    }
}
