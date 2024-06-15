package test.reactive.mytest;

import java.util.concurrent.CompletableFuture;

public class ThenApplyExample {

    public static void main(String[] args) {
        CompletableFuture<String> taskCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello from Task 1::supplyAsync::" + Thread.currentThread().getName());
            return "Hey";
        });

        System.out.println("Hello from Main::" + Thread.currentThread().getName());

        CompletableFuture<String> stringCompletableFuture = taskCompletableFuture.thenApplyAsync(data -> {
            System.out.println("Hello from Task 1::thenApplyAsync::" + Thread.currentThread().getName());
            return data + " Developers!";
        });
        String result = stringCompletableFuture.join();
        System.out.println(result);
    }
}
