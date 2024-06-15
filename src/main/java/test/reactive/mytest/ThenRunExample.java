package test.reactive.mytest;

import java.util.concurrent.CompletableFuture;

public class ThenRunExample {

    public static void main(String[] args) {
        CompletableFuture<String> taskCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello from Task 1::supplyAsync::" + Thread.currentThread().getName());
            return "Hey";
        });
        System.out.println("Hello from Main::" + Thread.currentThread().getName());
        Runnable runnable = () -> System.out.println("Finishing Task 1::thenRunAsync::" + Thread.currentThread().getName());
        taskCompletableFuture.thenRunAsync(runnable).join();
    }
}
