package com.alejandro.projectreactor.operators;

import com.alejandro.projectreactor.operators.User;
import reactor.core.publisher.Flux;

public class UserService {

    public  static Flux<User> getUsers() {
        return Flux.range(1,2)
                .map(User::new);
    }
}
