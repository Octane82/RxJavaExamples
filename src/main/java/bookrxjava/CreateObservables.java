package bookrxjava;

import rx.Observable;

import java.util.concurrent.TimeUnit;

/**
 *
 */
public class CreateObservables {


    /**
     * Observable.just(value)         -- nолько одно значение
     * Observable.from(values)        -- применяет Iterable<T>
     * Observable.range(from, n)      -- диапазон (от, кол-во элементов)
     *
     * -- Фиктивные --
     * Observable.empty()             -- завершается немедленно после подписки
     * Observable.never()             -- каждый Observable никогда не выбрасывает значений. (Используется при тестах)
     * Observable.error()             -- немедленно выбрасывает onError() после подписки
     *
     * @param args
     */
    public static void main(String[] args) {

        new CreateObservables().startMethod();

    }


    /**
     * Observable<Integer> ints =
     * Observable.<Integer>create(subscriber -> {
     * //...
     * Creating Observables
     * <p>
     * )
     * .cache();    -- для того чтобы не пересоздавать (Observable.create()) - каждый раз
     */


    private void startMethod() {

        System.out.println("Method started ...");

        delayed(5).subscribe(d -> System.out.println(d));

    }


    /**
     * emits value x after sleeping for 10 seconds.
     *
     * @param x
     * @param <T>
     * @return
     */
    // TODO: Пример !!! (не надо явно создавать внутри потоки)
    static <T> Observable<T> delayed(T x) {
        return Observable.create(
                subscriber -> {
                    Runnable r = () -> {
                        sleep(10, TimeUnit.SECONDS);
                        if (!subscriber.isUnsubscribed()) {
                            subscriber.onNext(x);
                            subscriber.onCompleted();
                        }
                    };
                    new Thread(r).start();
                });
    }


    static void sleep(int timeout, TimeUnit unit) {
        try {
            unit.sleep(timeout);
        } catch (InterruptedException ignored) {
            //intentionally ignored
        }
    }


    // ********************************

    /**
     * Хорошая практика обёртывать в try-catch чтобы передавать ошибки вниз по вызовам
     *
     * @param id
     * @return
     */
    /*Observable<String> rxLoad(int id) {
        return Observable.create(subscriber -> {
            try {
                subscriber.onNext(load(id));
                subscriber.onCompleted();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });
    }*/


    /**
     * Наиболнн короткий вариант метода сверху
     * @param id
     * @return
     */
    Observable<String> rxLoad(int id) {
        return Observable.fromCallable(() ->
                load(id));
    }


    /**
     * Empty method - for Example
     * @param id
     * @return
     */
    private String load(int id) {
        //
        return "";
    }


}
