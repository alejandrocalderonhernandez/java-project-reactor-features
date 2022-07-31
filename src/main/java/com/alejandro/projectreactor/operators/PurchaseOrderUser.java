package com.alejandro.projectreactor.operators;

import com.alejandro.projectreactor.Utils;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PurchaseOrderUser {

    private String item;
    private  String price;
    private int userId;

    public PurchaseOrderUser(int userId) {
        this.userId = userId;
        this.item = Utils.getFaker().commerce().productName();
        this.price = Utils.getFaker().commerce().price();
    }
}
