package com.alejandro.projectreactor.hot_publishers;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class InventoryService {

    private Map<String, Integer> db = new HashMap<>();

    public InventoryService() {
        this.db.put("Kids", 100);
        this.db.put("Automotive", 100);
    }

    public Consumer<PurchaseOrder> subscribeOrderStream() {
        return p -> db.computeIfPresent(p.getCategory(), (k, v) -> v + p.getQuantity());
    }

    public Flux<String> inventoryStream() {
        return  Flux.interval(Duration.ofSeconds(1))
                .map(i -> db.toString());
    }
}
