package com.alejandro.projectreactor.hot_publishers;

import com.alejandro.projectreactor.Utils;

public class Assigment {

    public static void main(String[] args) {
        OrdersService ordersService = new OrdersService();
        RevenueService revenueService = new RevenueService();
        InventoryService inventoryService = new InventoryService();

        ordersService.orderStream().subscribe(revenueService.subscribeOrderStream());
        ordersService.orderStream().subscribe(inventoryService.subscribeOrderStream());

        inventoryService.inventoryStream().subscribe(Utils.subscriber("inventory"));
        revenueService.revenueStream().subscribe(Utils.subscriber("revenue"));

        Utils.sleepInSeconds(60L);
    }
}
