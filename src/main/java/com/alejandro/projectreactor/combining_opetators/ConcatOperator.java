package com.alejandro.projectreactor.combining_opetators;

import com.alejandro.projectreactor.Utils;
import reactor.core.publisher.Flux;

public class ConcatOperator {

    public static void main(String[] args) {
        var flux1 = Flux.just("a", "b");
        var flux2 =Flux.just("1", "2", "3");
        var fluxError = Flux.error(new RuntimeException());
       var fluxConcatenated = Flux.concat(flux1, flux2, fluxError);

        fluxConcatenated.subscribe(Utils.subscriber());

    }
}
