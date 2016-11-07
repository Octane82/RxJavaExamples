package bookrxjava;

import rx.Observable;

import java.util.concurrent.TimeUnit;

import static rx.Observable.timer;

/**
 * Created
 */
public class OperatorsAndTransformations {


    public static void main(String[] args) {

        new OperatorsAndTransformations().operators();
    }


    Observable<String> observable = Observable.just("This example string");

    /**
     *
     */
    private void operators() {

        // filter - фильтрует вывод
        observable.filter(s -> s.startsWith("$"));

        // map - различные преобразования (например декодинг JSON в объект и т.д.)
        //Observable<Date> dates = tweets.map((Status status) -> status.getCreatedAt());

        Observable
                .just(8, 9, 10)
                .filter(i -> i % 3 > 0)
                .map(i -> "#" + i * 10)
                .filter(s -> s.length() < 4);
                //.subscribe(s -> System.out.println("OUT: " + s));

        Observable
                .just(8, 9, 10)
                .doOnNext(i -> System.out.println("A: " + i))
                .filter(i -> i % 3 > 0)
                .doOnNext(i -> System.out.println("B: " + i))
                .map(i -> "#" + i * 10)
                .doOnNext(s -> System.out.println("C: " + s))
                .filter(s -> s.length() < 4);
                //.subscribe(s -> System.out.println("D: " + s));

        // flatMap - может вернуть нам любой Observable
        // flatMap как map только возвращает Observable
            //numbers.flatMap(x -> Observable.just(7));

        // Sample
        /*List<Order> getOrders()
        Observable<Order> orders = customers
                .flatMap(customer ->
                        Observable.from(customer.getOrders()));*/


        // delay -
        Observable.just(1, 2, 3, 4, 5)
                .delay(5, TimeUnit.SECONDS)
                .subscribe(System.out::println);

        timer(5, TimeUnit.SECONDS)    // timer - если flatMap
                .flatMap(i -> Observable.just(1, 2, 3))
                .subscribe(i -> System.out.println(i));

        Observable
                .just("Lorem", "ipsum", "dolor", "sit", "amet",
                        "consectetur", "adipiscing", "elit")
                .flatMap(word -> timer(word.length(), TimeUnit.SECONDS)
                        .map(x -> word))
                .subscribe(s -> System.out.println("Timer: " + s));

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // -------------------------------------------------

        // merge - объединение потоков
        /*Observable<LicensePlate> fastAlgo(CarPhoto photo) {
            //Fast but poor quality
        }
        Observable<LicensePlate> preciseAlgo(CarPhoto photo) {
            //Precise but can be expensive
        }
        Observable<LicensePlate> experimentalAlgo(CarPhoto photo) {
            //Unpredictable, running anyway
        }
        // merge stream
        Observable<LicensePlate> all = Observable.merge(
                preciseAlgo(photo),
                fastAlgo(photo),
                experimentalAlgo(photo)
        );*/

        // Observable.mergeDelayError() - накапливает ошибки, если есть, и выбрасывает rx.exceptions.CompositeException

        // zip, zipWith  -- ???

        /*Observable<Temperature> temperatureMeasurements = station.temperature();
        Observable<Wind> windMeasurements = station.wind();
        temperatureMeasurements
                .zipWith(windMeasurements,
                        (temperature, wind) -> new Weather(temperature, wind));*/

        System.out.println("/ ************************************************************* /");

        // combineLatest - комбинирует потоки и упорядочивает (см. marble)
        Observable.combineLatest(
                interval(17, TimeUnit.MILLISECONDS).map(x -> "S" + x),
                interval(10, TimeUnit.MICROSECONDS).map(x -> "F" + x),
                (s, f) -> f + ":" + s
        ).forEach(System.out::println);


        // withLatestFrom
        // the slow stream is primary

        Observable
                .just(1, 2)
                .startWith(0)                              // --- Начинать с нуля
                .subscribe(System.out::println);

        System.out.println("****************************************");
        Observable.amb(
                stream(100, 17, "S"),
                stream(200, 10, "F")).subscribe(s -> System.out.println(s));

    }


    Observable<String> stream(int initialDelay, int interval, String name) {
        return Observable
                .interval(initialDelay, interval, TimeUnit.MILLISECONDS)
                .map(x -> name + x)
                .doOnSubscribe(() ->
                                System.out.println("Subscribe to " + name))

                .doOnUnsubscribe(() ->
                    System.out.println("Unsubscribe from " + name) );
    }



    private Observable<Integer> interval(int timeout, TimeUnit unit) {
        try {
            unit.sleep(timeout);
            return Observable.just(0,1, 2, 3);
        } catch (InterruptedException ignored) {
            //intentionally ignored
            return Observable.just(1);
        }
    }




}
