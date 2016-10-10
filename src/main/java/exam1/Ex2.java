package exam1;


import rx.Observable;
import rx.functions.Func1;

/**
 *  Операторы могут быть использованы в промежутке
 *  между Observable и Subscriber для манипуляции данными
 */
public class Ex2 {

    public static void main(String[] args) {

        new Ex2().sample1();

    }


    /**
     * RUN
     */
    private void sample1() {

        Observable.just("Hello world")
                .map(new Func1<String, String>() {                  // через оператор .map можно преобразовывать один элемент данных в другой: (в разрыв Observable и Subscriber)
                    @Override
                    public String call(String s) {
                        return s + " -Dima";
                    }
                })
                .subscribe(s -> System.out.println(s));


        // Также можем изменять на выход значение другого типа
        Observable.just("Hello HASH!")
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return s.hashCode();
                    }
                })
                .subscribe(i -> System.out.println(Integer.toString(i)));


        //
        Observable.just("Hello, world!")
                .map(s -> s.hashCode())
                .map(i -> Integer.toString(i))
                .subscribe(s -> System.out.println(s));


    }



}

