package com.alejandro.projectreactor.operators;

import com.alejandro.projectreactor.Utils;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {

    private int userId;
    private String name;

    public User(int userId) {
        this.userId = userId;
        this.name = Utils.getFaker().name().fullName();
    }
}
