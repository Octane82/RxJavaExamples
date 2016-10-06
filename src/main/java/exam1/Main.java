package exam1;

import rx.Observable;

import java.util.Arrays;
import java.util.List;

/**
 * Created by octane on 10/6/16.
 */
public class Main {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("Hello", "Streams", "Not");
        Observable.from(list).
                filter(s -> s.contains("e")).
                map(s -> s.toUpperCase()).
                reduce(new StringBuilder(), StringBuilder::append).
                subscribe(System.out::print, e -> {},
                        () -> System.out.println("!"));
    }



}
