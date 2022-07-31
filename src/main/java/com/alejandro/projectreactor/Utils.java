package com.alejandro.projectreactor;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import java.util.function.Consumer;

public class Utils {

    private static  final Faker FAKER = Faker.instance();
    public  static Consumer<Object>  onNext() {
        return  o -> System.out.println("Received: " + o.toString());
    }

    public  static Consumer<Throwable>  onError() {
        return  e -> System.out.println("Error: " + e.getMessage());
    }

    public  static Runnable onComplete() {
        return () -> System.out.println("Complete success");
    }

    public static Faker getFaker() { return FAKER; }

    public static void sleepInSeconds(Long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    public static void sleepInMillis(Long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    public static Subscriber<Object> subscriber() {
        return new MySubscriber();
    }

    public static Subscriber<Object> subscriber(String n) {
        return new MySubscriber(n);
    }
}
