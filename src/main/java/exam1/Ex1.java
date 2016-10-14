package exam1;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;


/**
 * Created
 */
public class Ex1 {

    public static void main(String[] args) {

        new Ex1().sample1();



    }


    private void sample1() {

        // Способ 1
            // myObservable.subscribe(mySubscriber);

        // Порождает один элемент данных, а потом завершает своё выполнение
        Observable<String> myObserv2 = Observable.just("Hello world. YAY");

        //myObserv2.subscribe(mySubscriber);

        // ******************
            // myObservable.subscribe(onNextActions);      // myObservable.subscribe(onNextAction, onErrorAction, onCompleteAction);


        // Сокращённая запись (с лямбдой)
        Observable.just("Hello sample").subscribe(s -> {
            System.out.println(s);
        });


        Observable.create(s ->
            {
                s.onNext("Hello everybody");
                s.onCompleted();
            })
                .subscribe(s -> System.out.println(s));



        // ********************
        getMyObservable()
                .map(s -> s + "YAY")
                .subscribe(s-> System.out.println(s));

    }


    /**
     * Излучает данные
     */
    Observable<String> myObservable = Observable.create(new Observable.OnSubscribe<String>() {
        @Override
        public void call(Subscriber<? super String> subscriber) {
            subscriber.onNext("Hello world!");
            subscriber.onCompleted();
        }
    });


    /**
     * Ловит данные (ПОДПИСЧИК)
     */
    Subscriber<String> mySubscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable throwable) {

        }

        @Override
        public void onNext(String s) {
            System.out.println(s);
        }
    };


    /**
     * 2 - й выпиант subscriber с вызывом одного метода
     */
   Action1<String> onNextActions = new Action1<String>() {
       @Override
       public void call(String s) {
           System.out.println(s);
       }
   };


    /**
     * Метод отдаёт Observable
     * @return
     */
    Observable<String> getMyObservable() {
        // return Observable.just("1) Generate observable string from METHOD: ");

        // ИЛИ

        return Observable.create(subscriber -> {
            subscriber.onNext("2) Generate observable string from METHOD:");
            subscriber.onCompleted();
        });
    }




}
