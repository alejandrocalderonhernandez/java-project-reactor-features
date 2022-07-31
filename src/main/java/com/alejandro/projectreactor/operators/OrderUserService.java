package com.alejandro.projectreactor.operators;

import com.alejandro.projectreactor.operators.PurchaseOrderUser;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderUserService {

    public static Map<Integer, List<PurchaseOrderUser>>  db = new HashMap<>();

    static {
        var list1 = Arrays.asList(
                new PurchaseOrderUser(1),
                new PurchaseOrderUser(1),
                new PurchaseOrderUser(1)
        );

        var list2 = Arrays.asList(
                new PurchaseOrderUser(2),
                new PurchaseOrderUser(2),
                new PurchaseOrderUser(2)
        );
        db.put(1, list1);
        db.put(2, list2);
    }

    public  static Flux<PurchaseOrderUser> getOrderById(int userId) {
        return  Flux.create(sink -> {
            db.get(userId).forEach(sink::next);
            sink.complete();
        });
    }
}
