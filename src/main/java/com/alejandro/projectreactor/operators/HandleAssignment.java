package com.alejandro.projectreactor.operators;

import com.alejandro.projectreactor.Utils;
import reactor.core.publisher.Flux;

public class HandleAssignment {
    public static void main(String[] args) {
        Flux.generate( sink -> sink.next(Utils.getFaker().country().name()))
                .map(Object::toString)
                .handle((name, sink) -> {
                    sink.next(name);
                    if (name.startsWith("C")) sink.complete();
                })
                .subscribe(Utils.subscriber());
    }
}
