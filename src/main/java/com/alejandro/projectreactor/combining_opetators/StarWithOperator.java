package com.alejandro.projectreactor.combining_opetators;

import com.alejandro.projectreactor.Utils;

public class StarWithOperator {

    public static void main(String[] args) {
        var nameGenerator = new Helper.NameGenerator();

        nameGenerator.generateName()
                .take(2)
                .subscribe(Utils.subscriber("Mike"));

        nameGenerator.generateName()
                .take(2)
                .subscribe(Utils.subscriber("Saul"));

        nameGenerator.generateName()
                .take(2)
                .subscribe(Utils.subscriber("lalo"));

    }
}
