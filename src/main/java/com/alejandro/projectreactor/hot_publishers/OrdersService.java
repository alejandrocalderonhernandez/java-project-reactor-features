package com.alejandro.projectreactor.hot_publishers;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Objects;

public class OrdersService {

    private Flux<PurchaseOrder> flux;

    public Flux<PurchaseOrder> orderStream() {
        if (Objects.isNull(this.flux)) {
            flux = getOrder();
        }
        return flux;
    }

    private Flux<PurchaseOrder> getOrder() {
        return  Flux.interval(Duration.ofMillis(50))
                .map(i -> new PurchaseOrder())
                .publish()
                .refCount(2);
    }
}
