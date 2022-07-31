package com.alejandro.projectreactor.operators;

import com.alejandro.projectreactor.Utils;
import reactor.core.publisher.Flux;

public class Handle {

    public static void main(String[] args) {
        Flux.range(1, 10)
                .handle((i, sink) -> {
                    //                      filter                               map
                    if ((i % 2) == 0) sink.next(i); else sink.next("odd");
                }).subscribe(Utils.subscriber());
    }
}
