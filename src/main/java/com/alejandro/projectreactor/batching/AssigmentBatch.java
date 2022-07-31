package com.alejandro.projectreactor.batching;

import com.alejandro.projectreactor.Utils;
import lombok.Data;
import lombok.ToString;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class AssigmentBatch {

    public static void main(String[] args) {
        Map<String, Function<Flux<PurchaseOrder>, Flux<PurchaseOrder>>> map = Map.of(
                "Kids", OrderProcessor.kidsProcessing(),
                "Automotive", OrderProcessor.automotiveProcessing()
        );

        Set<String > keys = map.keySet();

        OrderService.orderStream()
                .filter(purchaseOrder -> keys.contains(purchaseOrder.getCategory()))
                .groupBy(PurchaseOrder::getCategory)
                .flatMap(groupFiltered -> map.get(groupFiltered.key()).apply(groupFiltered))
                .subscribe(Utils.subscriber());

        Utils.sleepInSeconds(60L);
    }
}

class OrderProcessor {

    public static Function<Flux<PurchaseOrder>, Flux<PurchaseOrder>> automotiveProcessing() {
        return  flux -> flux
                .doOnNext(purchase -> purchase.setPrice(1.1 * purchase.getPrice()))
                .doOnNext(purchase -> purchase.setItem("{{ "+ purchase.getItem() + " }}"));
    }

    public static Function<Flux<PurchaseOrder>, Flux<PurchaseOrder>> kidsProcessing() {
        return  flux -> flux
                .doOnNext(purchase -> purchase.setPrice(0.5 * purchase.getPrice()))
                .flatMap(purchase -> Flux.concat(Mono.just(purchase) , getFreeKidsOrder()));
    }

    private static Mono<PurchaseOrder> getFreeKidsOrder() {
        return Mono.fromSupplier(() -> {
            PurchaseOrder purchaseOrder = new PurchaseOrder();
            purchaseOrder.setPrice(0.0);
            purchaseOrder.setItem("FREE: " + purchaseOrder.getItem());
            purchaseOrder.setCategory("Kids");
            return purchaseOrder;
        });
    }
}

class OrderService {

    public static Flux<PurchaseOrder> orderStream() {
        return  Flux.interval(Duration.ofMillis(50))
                .map(i -> new PurchaseOrder());
    }
}

@Data
@ToString
class PurchaseOrder {

    private String item;
    private  Double price;
    private String category;
    private Integer quantity;

    public PurchaseOrder() {
        this.item = Utils.getFaker().commerce().productName();
        this.price = Double.parseDouble(Utils.getFaker().commerce().price());
        this.category = Utils.getFaker().commerce().department();
        this.quantity = Utils.getFaker().random().nextInt(1, 10);
    }

}
