package test.reactive.mytest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class ThenCombineExample {
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

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        CompletableFuture<String> weatherEmailFuture = getUserEmail()
                .thenCombineAsync(getWeather(), (email, weather) -> {
                    System.out.println("Sending email to:::" + email + " Weather report => " + weather);
                    System.out.println("Sending email:::" + Thread.currentThread().getName());
                    return email + " => " + weather;
                });
        System.out.println("Hello from Main::" + Thread.currentThread().getName());
        sleep(4);
        String email = weatherEmailFuture.join();
        System.out.println(email);
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken::" + (endTime - startTime) / 1000);
    }
}
