package test.reactive.mytest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class SupplyAsyncExample {

    public static void main(String[] args) {

        Supplier<String> supplier = () -> {
            System.out.println("Hello from Task 1::" + Thread.currentThread().getName());
            return "Hello from Task 1::" + Thread.currentThread().getName();
        };


        CompletableFuture<String> taskCompletableFuture = CompletableFuture.supplyAsync(supplier);
        System.out.println("Hello from Main::" + Thread.currentThread().getName());
        String value = taskCompletableFuture.join();
        System.out.println("Value 1::" + value);


        Supplier<String> supplier2 = () -> {
            System.out.println("Hello from Task 2::" + Thread.currentThread().getName());
            return "Hello from Task 1::" + Thread.currentThread().getName();
        };
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletableFuture<String> taskCompletableFuture2 = CompletableFuture.supplyAsync(supplier2, executorService);
        String value2 = taskCompletableFuture2.join();
        System.out.println("Value 2::" + value2);
        executorService.shutdown();
    }
}
