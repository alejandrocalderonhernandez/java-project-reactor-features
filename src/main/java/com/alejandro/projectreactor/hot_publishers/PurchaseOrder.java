package com.alejandro.projectreactor.hot_publishers;

import com.alejandro.projectreactor.Utils;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PurchaseOrder {

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
