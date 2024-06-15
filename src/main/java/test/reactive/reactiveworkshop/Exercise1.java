package test.reactive.reactiveworkshop;

public class Exercise1 {

    public static void main(String[] args) {

        // Use StreamSources.intNumbersStream() and StreamSources.userStream()

        // Print all numbers in the intNumbersStream stream
        StreamSources.intNumbersStream().forEach(integer -> System.out.println(integer));
        System.out.println("-----------1----------");
        // Print numbers from intNumbersStream that are less than 5
        StreamSources.intNumbersStream().filter(integer -> integer < 5)
                .forEach(integer -> System.out.println(integer));
        System.out.println("-----------2----------");


        // Print the second and third numbers in intNumbersStream that's greater than 5
        StreamSources.intNumbersStream()
                .peek(n1 -> System.out.println(" initial  numbers " + n1))
                .filter(integer -> integer > 5)
                .peek(n2 -> System.out.println(" filtered numbers " + n2))
                .skip(1)
                .peek(n3 -> System.out.println(" filtered numbers after skip 1 " + n3))
                .limit(2)
                .peek(n4 -> System.out.println(" filtered numbers after limit 2 " + n4))
                .forEach(n5 -> System.out.println(n5));
        System.out.println("-----------3----------");

        //  Print the first number in intNumbersStream that's greater than 5.
        //  If nothing is found, print -1
        // TODO: Write code here
        Integer result = StreamSources.intNumbersStream().filter(integer -> integer > 5).peek(
                integer -> System.out.println("Filtered value " + integer)
        ).findFirst().orElse(-1);
        System.out.println(result);
        System.out.println("-----------4----------");

        // Print first names of all users in userStream
        StreamSources.userStream().forEach(user -> System.out.println(user.getFirstName()));
        StreamSources.userStream().map(user -> user.getFirstName()).forEach(System.out::println);
        System.out.println("-----------5----------");

        // Print first names in userStream for users that have IDs from number stream

        StreamSources.userStream()
                .filter(user -> StreamSources.intNumbersStream().anyMatch(integer -> user.getId() == integer))
                .map(user -> user.getFirstName())
                .forEach(System.out::println);

        System.out.println("-----------6.1----------");

        StreamSources.intNumbersStream()
                .flatMap(integer -> StreamSources.userStream().filter(user -> user.getId() == integer))
                .map(user -> user.getFirstName()).forEach(System.out::println);

        System.out.println("-----------6.2----------");

    }

}
