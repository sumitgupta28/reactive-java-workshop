package test.reactive.mytest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class ThenComposeExample {

    private static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static CompletableFuture<Map<String, String>> getUserDetails() {
        return CompletableFuture.supplyAsync(() -> {
            sleep(5);
            System.out.println("getUserDetails:::" + Thread.currentThread().getName());
            return getUser();
        });
    }

    private static Map<String, String> getUser() {
        Map<String, String> user = new ConcurrentHashMap<>();
        user.put("userId", "1234");
        user.put("userName", "salitha");
        user.put("phoneNo", "0777123456");
        return user;
    }

    private static CompletableFuture<List<String>> getPayments(String userName) {
        return CompletableFuture.supplyAsync(() -> {
            sleep(7);
            System.out.println("getPayments:::" + Thread.currentThread().getName());
            return Arrays.asList(
                    "USER: " + userName + " => $100",
                    "USER: " + userName + " => $65"
            );
        });
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        CompletableFuture<List<String>> paymentsFuture = getUserDetails()
                .thenComposeAsync(userData -> {
                    return getPayments(userData.get("userName"));
                });

        System.out.println("Hello from Main::" + Thread.currentThread().getName());
        sleep(4);
        List<String> payments = paymentsFuture.join();
        System.out.println(payments);
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken::" + (endTime - startTime) / 1000);
    }
}
