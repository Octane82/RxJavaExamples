package bookrxjava;

/**
 * Created
 */
public class CombiningStreams {

    public static void main(String[] args) {
        new CombiningStreams().exampleOne();
    }



    private void exampleOne() {

        // concat - объединяет потоки. Когда первый Observable завершается
        // concat подписывается на второй Observable. (!!! подписывается, если только первый выполнен)
        // Observable<Data> ends = Observable.concat(obser1, obser2);


        // результат 1-й элемент, который быстрее вычислится
        /*Observable<Car> fromCache = loadFromCache();
        Observable<Car> fromDb = loadFromDb();
        Observable<Car> found = Observable
                .concat(fromCache, fromDb)
                .first();*/





    }


}
