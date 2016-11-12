package bookrxjava;

import rx.Observable;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created
 */
public class AdvancedOperators {


    public static void main(String[] args) {
        new AdvancedOperators().exampleOne();
    }




    private void exampleOne() {

        // scan - накапливает значения
        Observable<BigInteger> factorials =  Observable
                .range(2, 100)
                .scan(BigInteger.ONE, (big, cur) ->
                        big.multiply(BigInteger.valueOf(cur)));


        // factorials.subscribe(bigInteger -> System.out.println(bigInteger));

        // reduce -
        Observable<List<Integer>> all = Observable
                .range(10, 20)
                .reduce(new ArrayList<>(), (list, item) -> {
                    list.add(item);
                    return list;
                });

        all.subscribe(l -> System.out.println(l));


        // collect -     (пример - агрегирование всех событий в StringBuilder)
        Observable<List<Integer>> all2 = Observable
                .range(10, 20)
                .collect(ArrayList::new, List::add);

        all2.subscribe(l -> System.out.println(l));

        Observable<String> str = Observable
                .range(1, 10)
                .collect(
                        StringBuilder::new,
                        (sb, x) -> sb.append(x).append(", "))
                .map(StringBuilder::toString);

        str.subscribe(s -> System.out.println(s));


        // distinct - (отдельный) Выбирает значение, не встречавшееся ранее (уникальное)
//        Observable<Integer> uniqueRandomInts = randomInts
//                .distinct()
//                .take(10);

        // distinctUntilChanged -


        // take, skip - взять и пропустить некоторые значения
        System.out.println("\nTake");
        Observable.range(1, 5).take(3).subscribe(i -> System.out.print(i + " "));
        System.out.println("\nSkip");
        Observable.range(1, 5).skip(3).subscribe(i -> System.out.print(i + " "));
        System.out.println("\nSkip");
        Observable.range(1, 5).skip(5).subscribe(i -> System.out.print(i + " "));

        // takeLast, skipLast - взять и пропустить некоторые последние значения
        System.out.println("\nTake last");
        Observable.range(1, 5).takeLast(2).subscribe(i -> System.out.print(i + " "));
        System.out.println("\nSkip last");
        Observable.range(1, 5).skip(2).subscribe(i -> System.out.print(i + " "));


        // first, last, takeFirst(predicate)
        //Observable.range(1, 5).first()


        // takeUntil, takeWhile
        Observable.range(1, 5).takeUntil(x -> x == 3);      // [1, 2, 3]
        Observable.range(1, 5).takeWhile(x -> x != 3);      // [1, 2]


        // elementAt - взять элемент по индексу
        System.out.println("\n*********************************");
        Observable.range(1, 5).elementAt(2).subscribe(i -> System.out.println(i));

        // ...orDefault

        // count - подсчитывает количество элементов
        Observable.just("p1", "p2", "p3", "p4").count().subscribe(s -> System.out.println("Emitted: " + s + " elements!"));


        // all, exists, contains
        Observable<Integer> numbers = Observable.range(1, 5);
        numbers.all(x -> x != 4);           // [false]
        numbers.exists(x -> x == 4);        // [true]
        numbers.contains(4);                // [true]


    }



}
