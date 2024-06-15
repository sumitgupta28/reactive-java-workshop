package test.reactive.mytest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureTest {

    private static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static CompletableFuture<String> getWeather() {
        return CompletableFuture.supplyAsync(() -> {
            sleep(5);
            System.out.println("getUserDetails:::" + Thread.currentThread().getName());
            return "Sunny, Temperature: 28C";
        });
    }

    private static CompletableFuture<String> getUserEmail() {
        return CompletableFuture.supplyAsync(() -> {
            sleep(5);
            System.out.println("getUserEmail:::" + Thread.currentThread().getName());
            return "john@gmail.com";
        });
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> userEmail = getUserEmail();
        CompletableFuture<String> wheather = getWeather();
        CompletableFuture<Void> bothCompletableFuture = CompletableFuture.allOf(userEmail, wheather);
        bothCompletableFuture.join();
        System.out.println(userEmail.get());
        System.out.println(wheather.get());

    }

}
