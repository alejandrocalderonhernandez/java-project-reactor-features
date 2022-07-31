package com.alejandro.projectreactor;
 import  org.reactivestreams.Subscriber;
 import org.reactivestreams.Subscription;

public class MySubscriber implements Subscriber<Object> {

    private String name = "";

    public MySubscriber(String name) {
        this.name = name;
    }

    public MySubscriber() {
    }

    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(Object o) {
        System.out.println(name + " received " + o);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println(name + "Error " + throwable.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println(name + "Complete ");

    }
}
