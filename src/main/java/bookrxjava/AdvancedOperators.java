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


        // 94/120


    }



}
