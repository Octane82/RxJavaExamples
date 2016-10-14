package exam1;

//import rx.Observable;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created
 */
public class Ex3 {


    public static void main(String[] args) {

        new Ex3().sampleFour();

    }




    private void sampleThree() {
        query("Hello everybody")
                .flatMap(new Func1<List<String>, Observable<String>>() {
                    @Override
                    public Observable<String> call(List<String> urls) {
                        return Observable.from(urls);
                    }
                })
                .subscribe(url -> System.out.println(url));

        // With lambda
        query("Hello, world!")
                .flatMap(urls -> Observable.from(urls))                 // flatMap - может вернуть нам любой Observable
                .subscribe(url -> System.out.println(url));

    }


    private void sampleFour() {
        // Объединяем методы, которые возвращают Observables
        query("Hello world")
                .flatMap(urls -> Observable.from(urls))
               .flatMap(new Func1<String, Observable<String>>() {
                   @Override
                   public Observable<String> call(String url) {
                       return getTitle(url);
                   }
               })
                .filter(title -> title != null)                         // Фильтруем значения на !null
                .take(3)                                                // Выводим только 3 значения
                .doOnNext(title -> saveTitle(title))                    // Добавляем некоторые дополнительные действия. Сохраняем полученные заголовки на диск
                .subscribe(title -> System.out.println(title));         // Вывод полученных заголовков
    }


    /**
     * Метод парсит ссылку на сайт и отдаёт
     * список заголовков
     * @param text - Ссылка на сайт
     * @return - Observable(список заголовков)
     */
    Observable<List<String>> query(String text){
        return Observable.create(subscriber -> {
            List<String> lst = new ArrayList<>();
            lst.add("Title one");
            lst.add(null);
            lst.add("Title two");
            lst.add("Title three");
            lst.add("Title four");
            lst.add("Title five");

            subscriber.onNext(lst);
            subscriber.onCompleted();
        });
    }



    // Возвращает заголовок вебсайта, или null, если мы получили ошибку 404

    /**
     * Метод возвращает список заголовков по входному URL
     * @param URL
     * @return
     */
    Observable<String> getTitle(String URL){
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (URL != null) {
                    subscriber.onNext("Title: " + URL);
                } else {
                    subscriber.onNext(null);        // null - если не найден заголовок -> фильтруем вывод
                }

                subscriber.onCompleted();
            }
        });
    }


    /**
     * Метод сохраняет полученный заголовок на диск
     * @param title
     */
    private void saveTitle(String title) {
        System.out.println("Save: " + title + " on disk!");
    }




}
