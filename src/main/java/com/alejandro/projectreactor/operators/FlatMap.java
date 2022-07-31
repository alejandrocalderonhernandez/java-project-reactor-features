package com.alejandro.projectreactor.operators;

import com.alejandro.projectreactor.Utils;

public class FlatMap {

    public static void main(String[] args) {
        UserService.getUsers()
                .flatMap(u -> OrderUserService.getOrderById(u.getUserId()))
                .subscribe(Utils.subscriber());
    }
}
