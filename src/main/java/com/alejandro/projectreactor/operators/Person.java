package com.alejandro.projectreactor.operators;

import com.alejandro.projectreactor.Utils;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Person {

    private String name;
    private Integer age;

    public Person() {
        this.name = Utils.getFaker().name().firstName();
        this.age = Utils.getFaker().random().nextInt(1, 10);
    }
}
