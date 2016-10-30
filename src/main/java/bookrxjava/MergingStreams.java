package bookrxjava;

import rx.Observable;

/**
 *
 *
 *
 */
public class MergingStreams {


    public static void main(String[] args) {
        // new Ex1().exampeOne();

        new MergingStreams().mergingStream();

        // Существуют также !!!!
        // Single<String> si =
        // Completable c = writeTodatabsee(Object data);
    }




    private void exampeOne() {
        Observable.create(s -> {
            s.onNext(1);
            s.onNext(2);
            s.onNext(3);})
                .map(i -> "Integer: " + i)
                .subscribe(s -> System.out.println(s));
    }


    /**
     * Объединение 2-х потоков в 3-й поток
     */
    private void mergingStream() {
        Observable<String> a = Observable.create(s -> {
            new Thread(() -> {
                s.onNext("One");
                s.onNext("Two");
            }).start();
        });

        Observable<String> b = Observable.create(s -> {
            new Thread(() -> {
                s.onNext("Three");
                s.onNext("Four");
            }).start();
        });

        // this subscribes to a and b concurrently,
        // and merges into a third sequential stream
        Observable<String> c = Observable.merge(a, b);


        // Если случается ошибка
        c.onErrorResumeNext(throwable -> Observable.create(subscriber -> {
            subscriber.onNext("Generate error!");
            subscriber.onCompleted();
        }))
        .subscribe(ct -> System.out.println(ct));

        // c.subscribe(ct -> System.out.println(ct));

    }



}
